package com.fintech.fraud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.fintech.fraud")
public class FraudEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(FraudEngineApplication.class, args);
	}

}
