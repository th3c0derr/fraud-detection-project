package com.fintech.fraud.rules;

import com.fintech.fraud.entity.Customer;
import com.fintech.fraud.entity.FraudRuleConfig;
import com.fintech.fraud.entity.Transaction;
import com.fintech.fraud.repository.FraudRuleConfigRepository;
import org.springframework.stereotype.Component;

@Component
public class CountryRule implements FraudRule {

    private final FraudRuleConfigRepository configRepository;

    public CountryRule(FraudRuleConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    @Override
    public RuleResult evaluate(Transaction tx, Customer customer) {

        FraudRuleConfig config = configRepository.findByRuleName("COUNTRY")
                .orElseThrow(() -> new IllegalArgumentException("Rule COUNTRY not configured in DB"));

        if (!config.isEnabled()) {
            return new RuleResult(0, "Country rule disabled", false);
        }

        if (config.getParameters() != null &&
                config.getParameters().contains(tx.getCountry())) {

            return new RuleResult(
                    config.getScore(),
                    "High-risk country: " + tx.getCountry(),
                    true
            );
        }

        return new RuleResult(0, "Country normal", false);
    }
}
