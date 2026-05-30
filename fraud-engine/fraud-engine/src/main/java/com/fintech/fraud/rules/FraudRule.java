package com.fintech.fraud.rules;

import com.fintech.fraud.entity.Customer;
import com.fintech.fraud.entity.Transaction;

public interface FraudRule {
    RuleResult evaluate(Transaction transaction, Customer customer);
}
