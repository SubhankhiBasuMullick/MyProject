package com.microservices.merchantOnboarding.merchantOnboarding.Controller;

import com.microservices.merchantOnboarding.merchantOnboarding.EntityModel.AuthNetworkSimulator;
import com.microservices.merchantOnboarding.merchantOnboarding.EntityModel.AuthTransaction;
import com.microservices.merchantOnboarding.merchantOnboarding.EntityModel.DepositNetworkSimulator;
import com.microservices.merchantOnboarding.merchantOnboarding.EntityModel.DepositTransaction;
import com.microservices.merchantOnboarding.merchantOnboarding.Repository.JpaAuthTransactionNetworkRepository;
import com.microservices.merchantOnboarding.merchantOnboarding.Repository.JpaAuthTransactionRepository;
import com.microservices.merchantOnboarding.merchantOnboarding.Repository.JpaDepositTransactionNetworkRepository;
import com.microservices.merchantOnboarding.merchantOnboarding.Repository.JpaDepositTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
@RestController
public class JpaDepositNetworkSimulatorController {


    @Autowired
    private JpaDepositTransactionRepository jpaDepositTransactionRepository;

    @Autowired
    private JpaDepositTransactionNetworkRepository jpaDepositTransactionNetworkRepository;

    @RequestMapping(value = "/jpa/depositNetworkStatusUpdate/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> depositNetwork( @PathVariable(value = "id") long depotransactionId,
                                            @RequestBody DepositNetworkSimulator depositNetworkSimulator) {
       try {
            DepositTransaction depoTransaction = jpaDepositTransactionRepository
                    .findBydepotransactionId(depotransactionId).get();
            // .orElseThrow(() -> new NoSuchElementException("User not found"));
//
////                DepositTransaction depo = new DepositTransaction();
////                depo.setUsername(depositTransaction.getUsername());
////                depo.setTransactionAmount(depositTransaction.getTransactionAmount());
////                depo.setTransactionDate(depositTransaction.getTransactionDate());
////                final DepositTransaction createdDeposit = jpaDepositTransactionRepository.save(depo);
////                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Merchant deposited succesfully");
//        } catch (NoSuchElementException e) {
//            return new ResponseEntity<>("No deposit merchant found", HttpStatus.NOT_FOUND);


        } catch (NoSuchElementException e) {

            return ResponseEntity.badRequest().body("Merchant not authorized");

        }
     //   }
        DepositNetworkSimulator dep = new DepositNetworkSimulator();
        dep.setDepositTransactionId(depotransactionId);
        dep.setStatus(depositNetworkSimulator.getStatus());
        dep.setReason(depositNetworkSimulator.getReason());
        //depo.setTransactionDate(depositTransaction.getTransactionDate());
        final DepositNetworkSimulator createdNetwork1 = jpaDepositTransactionNetworkRepository.save(dep);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Network simulator updated status succesfully");
    }

}
