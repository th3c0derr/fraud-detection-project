package com.fintech.fraud.domain.service;

import com.fintech.fraud.domain.model.FraudResult;
import com.fintech.fraud.entity.Customer;
import com.fintech.fraud.entity.Transaction;
import com.fintech.fraud.enums.RiskLevel;
import com.fintech.fraud.enums.TransactionStatus;
import com.fintech.fraud.rules.FraudRule;
import com.fintech.fraud.rules.RuleResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FraudEngine {

    private final List<FraudRule> rules;

    public FraudEngine(List<FraudRule> rules) {
        this.rules = rules;
    }

    public FraudResult evaluate(Transaction transaction, Customer customer){

        int riskScore = 0;
        List<String> reasons = new ArrayList<>();

        for (FraudRule rule : rules){
            RuleResult result = rule.evaluate(transaction, customer);

            if (result.isTriggered()) {
                riskScore += result.getScore();
                reasons.add(result.getReason());
            }
        }

        return buildResult(riskScore, reasons);
    }

    private FraudResult buildResult(int riskScore, List<String> reasons){
        RiskLevel level;

        if (riskScore >= 70) {
            level = RiskLevel.HIGH;
            
        } else if (riskScore >= 40) {
            level = RiskLevel.MEDIUM;
        } else {
            level = RiskLevel.LOW;
        }

        TransactionStatus status;

        if (riskScore >= 70) {
            status = TransactionStatus.REJECTED;
        }
        else {
            status = TransactionStatus.APPROVED;
        }

        return new FraudResult(
                riskScore,
                level,
                reasons,
                status
        );
    }
}
