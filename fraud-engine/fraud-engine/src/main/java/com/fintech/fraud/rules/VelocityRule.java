package com.fintech.fraud.rules;

import com.fintech.fraud.entity.Customer;
import com.fintech.fraud.entity.FraudRuleConfig;
import com.fintech.fraud.entity.Transaction;
import com.fintech.fraud.repository.FraudRuleConfigRepository;
import org.springframework.stereotype.Component;

@Component
public class VelocityRule implements FraudRule {

    private final FraudRuleConfigRepository configRepository;

    public VelocityRule(FraudRuleConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    @Override
    public RuleResult evaluate(Transaction tx, Customer customer) {

        FraudRuleConfig config = configRepository.findByRuleName("VELOCITY")
                .orElseThrow(() -> new IllegalArgumentException("Rule VELOCITY not configured in DB"));

        if (!config.isEnabled()) {
            return new RuleResult(0, "Velocity disabled", false);
        }

        boolean suspicious = true; // luego lo mejoras con queries reales

        if (suspicious) {
            return new RuleResult(
                    config.getScore(),
                    "High velocity detected",
                    true
            );
        }

        return new RuleResult(0, "Velocity normal", false);
    }
}
