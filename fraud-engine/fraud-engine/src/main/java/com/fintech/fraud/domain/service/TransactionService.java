package com.fintech.fraud.domain.service;

import com.fintech.fraud.domain.model.FraudResult;
import com.fintech.fraud.dto.TransactionDashboardResponse;
import com.fintech.fraud.dto.TransactionRequest;
import com.fintech.fraud.entity.*;
import com.fintech.fraud.mapper.TransactionMapper;
import com.fintech.fraud.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

//      1. recibe Transaction
//      2. obtiene Customer
//      3. ejecuta FraudEngine
//      4. decide APPROVED / REJECTED
//      5. guarda Transaction
//      6. crea FraudAlert si hace falta
//      7. devuelve FraudResult

    private final FraudEngine fraudEngine;
    private final TransactionMapper transactionMapper;
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final FraudPersistenceService fraudPersistenceService;
    private final TransactionRepository transactionRepository;
    private final FraudEvaluationRepository fraudEvaluationRepository;

    public TransactionService(FraudEngine fraudEngine, TransactionMapper transactionMapper, CustomerRepository customerRepository, AccountRepository accountRepository, FraudPersistenceService fraudPersistenceService, TransactionRepository transactionRepository, FraudEvaluationRepository fraudEvaluationRepository) {
        this.fraudEngine = fraudEngine;
        this.transactionMapper = transactionMapper;
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.fraudPersistenceService = fraudPersistenceService;
        this.transactionRepository = transactionRepository;
        this.fraudEvaluationRepository = fraudEvaluationRepository;
    }

    @Transactional
    public FraudResult process(TransactionRequest request) {

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Account account = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        Transaction tx = transactionMapper.toEntity(request, customer, account);

        FraudResult result = fraudEngine.evaluate(tx, customer);

        tx.setStatus(result.getFinalStatus());

        // Persistence delegated
        System.out.println("TX BEFORE SAVE");
        fraudPersistenceService.saveTransaction(tx);
        System.out.println("TX AFTER SAVE");

        FraudEvaluation evaluation = new FraudEvaluation();
        evaluation.setTransaction(tx);
        evaluation.setCustomer(customer);
        evaluation.setRiskScore(result.getRiskScore());
        evaluation.setRiskLevel(result.getRiskLevel());
        evaluation.setReasons(String.join(", ", result.getReasons()));
        evaluation.setFinalStatus(result.getFinalStatus());

        System.out.println("RISK LEVEL: " + result.getRiskLevel());
        System.out.println("STATUS: " + result.getFinalStatus());

        fraudPersistenceService.saveEvaluation(evaluation);

        fraudPersistenceService.saveAlertIfNeeded(tx, customer, result);

        return result;
    }

    public List<TransactionDashboardResponse> getAllTransactions() {
        return fraudEvaluationRepository.findAll()
                .stream()
                .map(evaluation -> {

                    Transaction transaction = evaluation.getTransaction();

                    return new TransactionDashboardResponse(
                            transaction.getId(),
                            transaction.getAmount(),
                            transaction.getCountry(),
                            evaluation.getRiskLevel() != null ? evaluation.getRiskLevel().name() : "LOW",
                            evaluation.getRiskScore(),
                            evaluation.getFinalStatus() != null ? evaluation.getFinalStatus().name() : "PENDING",
                            transaction.getTimestamp()
                    );
                })
                .toList();
    }
}

