package com.microservices.merchantOnboarding.merchantOnboarding.Repository;

import com.microservices.merchantOnboarding.merchantOnboarding.EntityModel.AuthNetworkSimulator;
import com.microservices.merchantOnboarding.merchantOnboarding.EntityModel.AuthTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;




    @Repository
    public interface JpaAuthTransactionNetworkRepository extends JpaRepository<AuthNetworkSimulator, Long> {


        AuthNetworkSimulator save(AuthNetworkSimulator authNetworkSimulator );


    }

