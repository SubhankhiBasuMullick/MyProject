package com.microservices.merchantOnboarding.merchantOnboarding.Controller;

import com.microservices.merchantOnboarding.merchantOnboarding.Component.JwtTokenUtil;
import com.microservices.merchantOnboarding.merchantOnboarding.Component.MyAuthenticationException;
import com.microservices.merchantOnboarding.merchantOnboarding.EntityModel.AuthNetworkSimulator;
import com.microservices.merchantOnboarding.merchantOnboarding.EntityModel.AuthTransaction;
import com.microservices.merchantOnboarding.merchantOnboarding.EntityModel.DepositNetworkSimulator;
import com.microservices.merchantOnboarding.merchantOnboarding.EntityModel.DepositTransaction;
import com.microservices.merchantOnboarding.merchantOnboarding.Model.LoginRequest;
import com.microservices.merchantOnboarding.merchantOnboarding.Model.MerchantDetails;
import com.microservices.merchantOnboarding.merchantOnboarding.Repository.JpaAuthTransactionRepository;
import com.microservices.merchantOnboarding.merchantOnboarding.Repository.JpaDepositTransactionNetworkRepository;
import com.microservices.merchantOnboarding.merchantOnboarding.Repository.JpaDepositTransactionRepository;
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
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class JpaDepositTransactionController {
    //
//
    @Value("${jwt.http.request.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Autowired
    private JpaAuthTransactionRepository jpaAuthTransactionRepository;

    @Autowired
    private JpaDepositTransactionRepository jpaDepositTransactionRepository;
    @Autowired
    private JpaDepositTransactionNetworkRepository jpaDepositTransactionNetworkRepository;

    @RequestMapping(value = "/jpa/depositStatusUpdate/{id}", method = RequestMethod.PUT)
    public ResponseEntity<DepositTransaction> updateDepositTransaction(
            //@PathVariable String username,
            @PathVariable(value = "id") long depositTransactionId,
            @RequestBody DepositNetworkSimulator depositNetworkSimulator) throws ResourceNotFoundException {

        DepositTransaction dep =
                jpaDepositTransactionRepository
                        .findById(depositTransactionId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + depositNetworkSimulator));
        dep.setStatus(depositNetworkSimulator.getStatus());
        dep.setReason(depositNetworkSimulator.getReason());
        final DepositTransaction depositTransaction = jpaDepositTransactionRepository.save(dep);
        return new ResponseEntity<DepositTransaction>(depositTransaction, HttpStatus.OK);
    }
//		catch (NoSuchElementException e) {
//			//AuthTransaction updatedAuthTransaction= jpaAuthTransactionRepository.save(authTransaction);
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//			//return new ResponseEntity<AuthTransaction>.OK


    @RequestMapping(value = "${jwt.deposit.uri}", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody DepositTransaction depositTransaction) {
        ArrayList<AuthTransaction> authTransaction = new ArrayList<AuthTransaction>();
        try {
            authTransaction = jpaAuthTransactionRepository.findByusername(depositTransaction.getUsername());
            for (AuthTransaction authitem : authTransaction) {


                if (authitem.getStatus().equalsIgnoreCase("approved")) {
                    DepositTransaction depo = new DepositTransaction();
                    depo.setUsername(depositTransaction.getUsername());
                    depo.setTransactionAmount(depositTransaction.getTransactionAmount());
                    depo.setTransactionDate(depositTransaction.getTransactionDate());
                    final DepositTransaction createdDeposit = jpaDepositTransactionRepository.save(depo);
                    if (getAuthenticatedByNetworkSimulator(createdDeposit.getDepotransactionId())) {
                        DepositTransaction dep = jpaDepositTransactionRepository.findBydepotransactionId(createdDeposit.getDepotransactionId()).get();
                        dep.setStatus("approved");
                        dep.setReason("valid");
                        DepositTransaction createdDepoTransaction1 = jpaDepositTransactionRepository.save(dep);

                    } else {
                        DepositTransaction dep = jpaDepositTransactionRepository.findBydepotransactionId(createdDeposit.getDepotransactionId()).get();
                        dep.setStatus("rejected");
                        dep.setReason("invalid");
                        DepositTransaction createdDepoTransaction1 = jpaDepositTransactionRepository.save(dep);
                    }

                    return ResponseEntity.status(HttpStatus.ACCEPTED).body("Merchant deposited succesfully");
                } else {
                    return ResponseEntity.badRequest().body("Auth id is not valid");
                }
            }

        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No auth merchant found", HttpStatus.NOT_FOUND);
        }

    }

//
    // return ResponseEntity.status(HttpStatus.ACCEPTED).body("Merchant deposited succesfully");

    private boolean getAuthenticatedByNetworkSimulator(Long depId) {

        //

        DepositNetworkSimulator networkSimulator = new DepositNetworkSimulator();
        networkSimulator.setDepositTransactionId(depId);
        int int_random = ThreadLocalRandom.current().nextInt();
        if (int_random % 2 == 0) {
            networkSimulator.setStatus("approved");
            networkSimulator.setReason("valid");
            jpaDepositTransactionNetworkRepository.save(networkSimulator);
            return true;
        } else {
            networkSimulator.setStatus("rejected");
            networkSimulator.setReason("invalid");
            jpaDepositTransactionNetworkRepository.save(networkSimulator);
            return false;
        }
    }
}

//        authenticate(authTransaction.getUsername());
//        DepositTransaction depositTransaction=new DepositTransaction();
//        depositTransaction.setStatus("pending");
//        depositTransaction.setUsername(authTransaction.getUsername());
//       // authTransaction.setPassword(loginRequestDto.getPassword());
//        authTransaction.setReason("pending");
//
//        //authentication aka puch tacha complete preparing JWT for the response, below
//        final MerchantDetails userDetails = (MerchantDetails) userDetailsService.loadUserByUsername(loginRequestDto.getUsername());
//
//        final String token = jwtTokenUtil.generateToken(userDetails);
//        AuthTransaction createdAuthTransaction = jpaAuthTransactionRepository.save(authTransaction);
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Merchant authenticated succesfully");
//        //return ResponseEntity.ok(new LoginResponse(token));
//
//
////		else {
////			return ResponseEntity.badRequest().body("NOT VALID");
////		}
//    }
//
//    @ExceptionHandler({MyAuthenticationException.class})
//    public ResponseEntity<String> handleAuthenticationException(MyAuthenticationException e) {
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
//    }
//
//    private void authenticate(String username) {
//        Objects.requireNonNull(username);
//        //Objects.requireNonNull(password);
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
//            authenticationManager.authenticate(new )
//        } catch (DisabledException e) {
//            throw new MyAuthenticationException("USER_DISABLED", e);
//            //return false;
//        } catch (BadCredentialsException e) {
//            throw new MyAuthenticationException("INVALID_CREDENTIALS", e);
//            //return  false;
//        }
//        //return true;
//    }
////
////
////
//@ResponseStatus(HttpStatus.BAD_REQUEST)
//@ExceptionHandler(MethodArgumentNotValidException.class)
//public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
//    Map<String, String> errors = new HashMap<>();
//
//    ex.getBindingResult().getFieldErrors().forEach(error ->
//            errors.put(error.getField(), error.getDefaultMessage()));
//
//    return errors;




