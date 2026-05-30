package com.fintech.fraud.rules;

import com.fintech.fraud.entity.Customer;
import com.fintech.fraud.entity.FraudRuleConfig;
import com.fintech.fraud.entity.Transaction;
import com.fintech.fraud.repository.FraudRuleConfigRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AmountRule implements FraudRule {

    private final FraudRuleConfigRepository configRepository;

    public AmountRule(FraudRuleConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    @Override
    public RuleResult evaluate(Transaction tx, Customer customer){

        FraudRuleConfig config = configRepository.findByRuleName("AMOUNT")
                .orElseThrow(() -> new IllegalArgumentException("Rule AMOUNT not configured in DB"));

        if (!config.isEnabled()) {
            return new RuleResult(0, "Amount rule disabled", false);
        }

        if (tx.getAmount().compareTo(BigDecimal.valueOf(config.getThreshold())) > 0) {
            return new RuleResult(
                    config.getScore(),
                    "Amount exceeds threshold: " + config.getThreshold(),
                    true
            );
        }

        return new RuleResult(0, "Amount normal", false);

    }

}
