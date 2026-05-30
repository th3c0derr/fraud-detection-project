package com.fintech.fraud.controller;

import com.fintech.fraud.domain.model.FraudResult;
import com.fintech.fraud.domain.service.TransactionService;
import com.fintech.fraud.dto.TransactionDashboardResponse;
import com.fintech.fraud.dto.TransactionRequest;
import com.fintech.fraud.entity.Transaction;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@CrossOrigin(origins = "http://localhost:5173")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public FraudResult process(@Valid @RequestBody TransactionRequest request){
        return transactionService.process(request);
    }

    @GetMapping
    public List<TransactionDashboardResponse> getAllTransactions() {
        return transactionService.getAllTransactions();
    }
}
