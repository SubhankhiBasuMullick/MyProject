package com.microservices.merchantOnboarding.merchantOnboarding.Controller;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;


import com.microservices.merchantOnboarding.merchantOnboarding.Component.JwtTokenUtil;
import com.microservices.merchantOnboarding.merchantOnboarding.Component.MyAuthenticationException;

import com.microservices.merchantOnboarding.merchantOnboarding.EntityModel.AuthNetworkSimulator;
import com.microservices.merchantOnboarding.merchantOnboarding.EntityModel.AuthTransaction;
import com.microservices.merchantOnboarding.merchantOnboarding.Model.LoginRequest;
import com.microservices.merchantOnboarding.merchantOnboarding.Model.LoginResponse;
import com.microservices.merchantOnboarding.merchantOnboarding.Model.MerchantDetails;
import com.microservices.merchantOnboarding.merchantOnboarding.Repository.JpaAuthTransactionNetworkRepository;
import com.microservices.merchantOnboarding.merchantOnboarding.Repository.JpaAuthTransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class JpaAuthTransactionController {
	//
//	
	@Value("${jwt.http.request.header}")
	private String tokenHeader;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;


	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JpaAuthTransactionRepository jpaAuthTransactionRepository;
	@Autowired
	private JpaAuthTransactionNetworkRepository jpaAuthTransactionNetworkRepository;

	@RequestMapping(value="/jpa/authStatusUpdate/{id}", method=RequestMethod.PUT)
	public ResponseEntity<AuthTransaction> updateAuthTransaction(
			//@PathVariable String username,
			@PathVariable (value= "id") long authTransactionId,
			@RequestBody AuthNetworkSimulator authNetworkSimulator) throws ResourceNotFoundException {

		AuthTransaction auth =
				jpaAuthTransactionRepository
						.findById(authTransactionId)
						.orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + authTransactionId));
//		AuthNetworkSimulator auth= jpaAuthTransactionNetworkRepository.findById(authTransactionId)
//				.orElseThrow(()-> new ResourceNotFoundException("No merchant found on:: "+authTransactionId));
		auth.setStatus(authNetworkSimulator.getStatus());
		auth.setReason(authNetworkSimulator.getReason());
		final AuthTransaction transaction = jpaAuthTransactionRepository.save(auth);
			return new ResponseEntity<AuthTransaction>(transaction,HttpStatus.OK);

//		catch (NoSuchElementException e) {
//			//AuthTransaction updatedAuthTransaction= jpaAuthTransactionRepository.save(authTransaction);
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//			//return new ResponseEntity<AuthTransaction>.OK
		}



	@RequestMapping(value = "${jwt.login.uri}", method = RequestMethod.POST)
	public ResponseEntity<?> login(  @RequestBody AuthTransaction authTransaction)
			throws MyAuthenticationException {

		authenticate(authTransaction.getUsername(), authTransaction.getPassword());
		AuthTransaction authTransaction1=new AuthTransaction();
		//authTransaction.setStatus("pending");
		authTransaction1.setUsername(authTransaction.getUsername());
		authTransaction1.setPassword(authTransaction.getPassword());
		authTransaction1.setTransactionAmount(authTransaction.getTransactionAmount());
		authTransaction1.setTransactionDate(authTransaction.getTransactionDate());
	//	authTransaction.setReason("pending");

		//authentication aka puch tacha complete preparing JWT for the response, below
		final MerchantDetails userDetails = (MerchantDetails) userDetailsService.loadUserByUsername(authTransaction.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);
		AuthTransaction createdAuthTransaction = jpaAuthTransactionRepository.save(authTransaction1);
	//	AuthTransaction ab=jpaAuthTransactionRepository.f

		if(getAuthenticatedByNetworkSimulator(createdAuthTransaction.getTransactionId()))
		{
			AuthTransaction auth=jpaAuthTransactionRepository.findBytransactionId(createdAuthTransaction.getTransactionId()).get();
			auth.setStatus("approved");
			auth.setReason("valid");
			AuthTransaction createdAuthTransaction1 = jpaAuthTransactionRepository.save(auth);

		}
		else {
			AuthTransaction auth=jpaAuthTransactionRepository.findBytransactionId(createdAuthTransaction.getTransactionId()).get();
			auth.setStatus("rejected");
			auth.setReason("invalid");
			AuthTransaction createdAuthTransaction1 = jpaAuthTransactionRepository.save(auth);
		}

		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Merchant validated and reviewed succesfully by payment processor and network simulator");
		//return ResponseEntity.ok(new LoginResponse(token));


//		else {
//			return ResponseEntity.badRequest().body("NOT VALID");
//		}
}

	@ExceptionHandler({MyAuthenticationException.class})
	public ResponseEntity<String> handleAuthenticationException(MyAuthenticationException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	}

	private void authenticate(String username, String password) {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new MyAuthenticationException("USER_DISABLED", e);
			//return false;
		} catch (BadCredentialsException e) {
			throw new MyAuthenticationException("INVALID_CREDENTIALS", e);
			//return  false;
		}
		//return true;
	}
	private boolean getAuthenticatedByNetworkSimulator(Long authId)
	{

		//

		AuthNetworkSimulator networkSimulator=new AuthNetworkSimulator();
		networkSimulator.setAuthTransactionId(authId);
		int int_random = ThreadLocalRandom.current().nextInt();
		System.out.println(int_random);
		if(int_random%2==0) {
			networkSimulator.setStatus("approved");
			networkSimulator.setReason("valid");
			jpaAuthTransactionNetworkRepository.save(networkSimulator);
			return true;
		}
		else {
			networkSimulator.setStatus("rejected");
			networkSimulator.setReason("invalid");
			jpaAuthTransactionNetworkRepository.save(networkSimulator);
			return false;
		}
		//jpaAuthTransactionRepository.save(auth);
		//jpaAuthTransactionNetworkRepository.save(networkSimulator);

		//return true;

	}


//	
//	
//
}
