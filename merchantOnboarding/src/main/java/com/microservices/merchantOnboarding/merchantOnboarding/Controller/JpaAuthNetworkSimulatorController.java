package com.microservices.merchantOnboarding.merchantOnboarding.Controller;

import com.microservices.merchantOnboarding.merchantOnboarding.EntityModel.AuthNetworkSimulator;
import com.microservices.merchantOnboarding.merchantOnboarding.EntityModel.AuthTransaction;
import com.microservices.merchantOnboarding.merchantOnboarding.EntityModel.DepositTransaction;
import com.microservices.merchantOnboarding.merchantOnboarding.Repository.JpaAuthTransactionNetworkRepository;
import com.microservices.merchantOnboarding.merchantOnboarding.Repository.JpaAuthTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
public class JpaAuthNetworkSimulatorController {

    @Autowired
    private JpaAuthTransactionRepository jpaAuthTransactionRepository;

    @Autowired
    private JpaAuthTransactionNetworkRepository jpaAuthTransactionNetworkRepository;

    @RequestMapping(value = "/jpa/authNetworkStatusUpdate/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> login(@PathVariable(value = "id") long authTransactionId ,
                                   @RequestBody AuthNetworkSimulator authNetworkSimulator) {
        try {
            AuthTransaction authTransaction = jpaAuthTransactionRepository
                    .findBytransactionId(authTransactionId).get();
            // .orElseThrow(() -> new NoSuchElementException("User not found"));

//                DepositTransaction depo = new DepositTransaction();
//                depo.setUsername(depositTransaction.getUsername());
//                depo.setTransactionAmount(depositTransaction.getTransactionAmount());
//                depo.setTransactionDate(depositTransaction.getTransactionDate());
//                final DepositTransaction createdDeposit = jpaDepositTransactionRepository.save(depo);
//                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Merchant deposited succesfully");
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No auth merchant found", HttpStatus.NOT_FOUND);


//        } catch (NoSuchElementException e) {
//
//            return ResponseEntity.badRequest().body("Merchant not authorized");
//
//        }
        }
        AuthNetworkSimulator net = new AuthNetworkSimulator();
        net.setAuthTransactionId(authTransactionId);
        net.setStatus(authNetworkSimulator.getStatus());
        net.setReason(authNetworkSimulator.getReason());
        //depo.setTransactionDate(depositTransaction.getTransactionDate());
        final AuthNetworkSimulator createdNetwork = jpaAuthTransactionNetworkRepository.save(net);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Network simulator updated status succesfully");
    }
}