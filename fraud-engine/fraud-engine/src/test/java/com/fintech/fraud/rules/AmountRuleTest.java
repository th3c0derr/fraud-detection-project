package com.fintech.fraud.rules;

import com.fintech.fraud.entity.Customer;
import com.fintech.fraud.entity.Transaction;
import com.fintech.fraud.repository.FraudRuleConfigRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class AmountRuleTest {

//    private final AmountRule rule = new AmountRule();
//
//    @Test
//    void shouldTriggerHighRisk_whenAmountGreaterThan10000() {
//
//        Transaction tx = new Transaction();
//        tx.setAmount(new java.math.BigDecimal("15000"));
//
//        RuleResult result = rule.evaluate(tx, new Customer());
//
//        assertTrue(result.isTriggered());
//        assertEquals(50, result.getScore());
//    }
//
//    @Test
//    void shouldTriggerMediumRisk_whenAmountGreaterThan5000() {
//        Transaction tx = new Transaction();
//        tx.setAmount(new java.math.BigDecimal("7000"));
//
//        RuleResult result = rule.evaluate(tx, new Customer());
//
//        assertTrue(result.isTriggered());
//        assertEquals(20, result.getScore());
//    }
//
//    @Test
//    void shouldNotTrigger_whenAmountLow() {
//
//        Transaction tx = new Transaction();
//        tx.setAmount(new java.math.BigDecimal("100"));
//
//        RuleResult result = rule.evaluate(tx, new Customer());
//
//        assertFalse(result.isTriggered());
//        assertEquals(0, result.getScore());
//    }

    private AmountRule amountRule;

    @BeforeEach
    void setup() {

        FraudRuleConfigRepository repository =
                Mockito.mock(FraudRuleConfigRepository.class);

        amountRule = new AmountRule(repository);
    }

    @Test
    void testRule() {

    }
}
