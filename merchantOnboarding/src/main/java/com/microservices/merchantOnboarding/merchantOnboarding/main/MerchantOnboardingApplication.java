package com.microservices.merchantOnboarding.merchantOnboarding.main;

import com.microservices.merchantOnboarding.merchantOnboarding.Repository.JpaAuthTransactionNetworkRepository;
import com.microservices.merchantOnboarding.merchantOnboarding.Repository.JpaAuthTransactionRepository;
import com.microservices.merchantOnboarding.merchantOnboarding.Repository.JpaDepositTransactionRepository;
import com.microservices.merchantOnboarding.merchantOnboarding.Repository.JpaMerchantRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.microservices.merchantOnboarding.merchantOnboarding")
//@ComponentScan(basePackages = {"com.microservices.merchantOnboarding.merchantOnboarding"}
@EntityScan("com.microservices.merchantOnboarding.merchantOnboarding.EntityModel")
@EnableJpaRepositories(basePackageClasses ={ JpaMerchantRepository.class, JpaAuthTransactionRepository.class,
		JpaDepositTransactionRepository.class, JpaAuthTransactionNetworkRepository.class})

public class MerchantOnboardingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MerchantOnboardingApplication.class, args);
	}

}
