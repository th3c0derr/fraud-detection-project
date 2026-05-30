package com.fintech.fraud.rules;

import com.fintech.fraud.entity.Customer;
import com.fintech.fraud.entity.Transaction;
import com.fintech.fraud.repository.FraudRuleConfigRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class CountryRuleTest {
//    private final CountryRule rule = new CountryRule();
//
//    @Test
//    void shouldTrigger_whenCountryIsNG(){
//        Transaction tx = new Transaction();
//        tx.setCountry("NG");
//
//        RuleResult result = rule.evaluate(tx, new Customer());
//
//        assertTrue(result.isTriggered());
//        assertEquals(40, result.getScore());
//    }
//
//    @Test
//    void shouldNotTrigger_whenCountrySafe() {
//
//        Transaction tx = new Transaction();
//        tx.setCountry("ES");
//
//        RuleResult result = rule.evaluate(tx, new Customer());
//
//        assertFalse(result.isTriggered());
//        assertEquals(0, result.getScore());
//    }

    private CountryRule countryRule;

    @BeforeEach
    void setup() {

        FraudRuleConfigRepository repository =
                Mockito.mock(FraudRuleConfigRepository.class);

        countryRule = new CountryRule(repository);
    }

    @Test
    void testRule() {

    }
}
