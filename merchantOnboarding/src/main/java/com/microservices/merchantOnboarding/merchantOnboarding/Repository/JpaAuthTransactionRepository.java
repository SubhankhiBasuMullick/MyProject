package com.microservices.merchantOnboarding.merchantOnboarding.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.microservices.merchantOnboarding.merchantOnboarding.EntityModel.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.merchantOnboarding.merchantOnboarding.EntityModel.AuthTransaction;




@Repository
public interface JpaAuthTransactionRepository extends JpaRepository<AuthTransaction, Long>{


	Optional<AuthTransaction> findByusername(String username);
	Optional<AuthTransaction> findBytransactionId(long transactionId);

}