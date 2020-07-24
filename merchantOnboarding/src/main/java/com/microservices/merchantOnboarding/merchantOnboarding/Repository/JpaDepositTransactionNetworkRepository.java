package com.microservices.merchantOnboarding.merchantOnboarding.Repository;


import com.microservices.merchantOnboarding.merchantOnboarding.EntityModel.AuthNetworkSimulator;
import com.microservices.merchantOnboarding.merchantOnboarding.EntityModel.DepositNetworkSimulator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaDepositTransactionNetworkRepository extends JpaRepository<DepositNetworkSimulator, Long> {


    DepositNetworkSimulator save(DepositNetworkSimulator depositNetworkSimulator );


}
