package com.fintech.fraud.domain.service;

import com.fintech.fraud.domain.model.FraudResult;
import com.fintech.fraud.entity.Customer;
import com.fintech.fraud.entity.FraudAlert;
import com.fintech.fraud.entity.FraudEvaluation;
import com.fintech.fraud.entity.Transaction;
import com.fintech.fraud.enums.ActionTaken;
import com.fintech.fraud.enums.RiskLevel;
import com.fintech.fraud.enums.TransactionStatus;
import com.fintech.fraud.repository.FraudAlertRepository;
import com.fintech.fraud.repository.FraudEvaluationRepository;
import com.fintech.fraud.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class FraudPersistenceService {

    private final TransactionRepository transactionRepository;
    private final FraudEvaluationRepository fraudEvaluationRepository;
    private final FraudAlertRepository fraudAlertRepository;

    public FraudPersistenceService(TransactionRepository transactionRepository, FraudEvaluationRepository fraudEvaluationRepository, FraudAlertRepository fraudAlertRepository) {
        this.transactionRepository = transactionRepository;
        this.fraudEvaluationRepository = fraudEvaluationRepository;
        this.fraudAlertRepository = fraudAlertRepository;
    }

    public void saveTransaction(Transaction tx){
        transactionRepository.save(tx);
    }

    public void saveEvaluation(FraudEvaluation evaluation){
        fraudEvaluationRepository.save(evaluation);
    }

    public void saveAlertIfNeeded(Transaction tx, Customer customer, FraudResult result){
        if (result.getRiskLevel() == RiskLevel.HIGH || result.getRiskLevel() == RiskLevel.MEDIUM) {

            FraudAlert alert = new FraudAlert();
            alert.setTransaction(tx);
            alert.setCustomer(customer);
            alert.setReason(String.join(", ", result.getReasons()));
            alert.setRiskLevel(result.getRiskLevel());

            alert.setActionTaken(
                    result.getFinalStatus() == TransactionStatus.REJECTED
                            ? ActionTaken.BLOCKED
                            : ActionTaken.REVIEW
            );

            fraudAlertRepository.save(alert);
        }
    }
}
