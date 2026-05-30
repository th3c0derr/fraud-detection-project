package com.fintech.fraud.domain.service;

import com.fintech.fraud.domain.model.FraudResult;
import com.fintech.fraud.entity.Customer;
import com.fintech.fraud.entity.FraudRuleConfig;
import com.fintech.fraud.entity.Transaction;
import com.fintech.fraud.repository.FraudRuleConfigRepository;
import com.fintech.fraud.rules.AmountRule;
import com.fintech.fraud.rules.CountryRule;
import com.fintech.fraud.rules.VelocityRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FraudEngineTest {

//    private final FraudEngine fraudEngine =
//            new FraudEngine(List.of(
//                    new AmountRule(),
//                    new CountryRule(),
//                    new VelocityRule()
//            ));
//
//    @Test
//    void shouldCalculateRiskScoreCorrectly() {
//
//        Customer customer = new Customer();
//        customer.setCountry("ES");
//
//        Transaction tx = new Transaction();
//        tx.setAmount(new java.math.BigDecimal("12000"));
//        tx.setCountry("NG");
//
//        FraudResult result = fraudEngine.evaluate(tx, customer);
//
//        assertTrue(result.getRiskScore() > 0);
//        assertFalse(result.getReasons().isEmpty());

    @Mock
    FraudRuleConfigRepository configRepository;

    FraudEngine fraudEngine;

    @BeforeEach
    void setup() {

        FraudRuleConfig config = new FraudRuleConfig();
        config.setEnabled(true);
        config.setThreshold(10000);
        config.setScore(50);

        when(configRepository.findByRuleName("AMOUNT"))
                .thenReturn(Optional.of(config));

        fraudEngine = new FraudEngine(List.of(
                new AmountRule(configRepository),
                new CountryRule(configRepository),
                new VelocityRule(configRepository)
        ));
    }

    @Test
    void shouldCalculateRiskScoreCorrectly() {

        Customer customer = new Customer();
        customer.setCountry("ES");

        Transaction tx = new Transaction();
        tx.setAmount(new BigDecimal("12000"));
        tx.setCountry("NG");

        FraudResult result = fraudEngine.evaluate(tx, customer);

        assertTrue(result.getRiskScore() > 0);
        assertFalse(result.getReasons().isEmpty());
    }
}

