package com.microservices.merchantOnboarding.merchantOnboarding.Repository;

import com.microservices.merchantOnboarding.merchantOnboarding.EntityModel.AuthTransaction;
import com.microservices.merchantOnboarding.merchantOnboarding.EntityModel.DepositTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaDepositTransactionRepository extends JpaRepository<DepositTransaction, Long> {


    Optional<DepositTransaction> findByusername(String username);


}
