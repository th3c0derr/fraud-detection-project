package com.fintech.fraud.domain.service;

import com.fintech.fraud.domain.model.FraudResult;
import com.fintech.fraud.dto.TransactionRequest;
import com.fintech.fraud.entity.Account;
import com.fintech.fraud.entity.Customer;
import com.fintech.fraud.entity.FraudAlert;
import com.fintech.fraud.entity.Transaction;
import com.fintech.fraud.enums.RiskLevel;
import com.fintech.fraud.enums.TransactionStatus;
import com.fintech.fraud.mapper.TransactionMapper;
import com.fintech.fraud.repository.AccountRepository;
import com.fintech.fraud.repository.CustomerRepository;
import com.fintech.fraud.repository.FraudAlertRepository;
import com.fintech.fraud.repository.TransactionRepository;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private FraudEngine fraudEngine;

    @Mock
    private FraudAlertRepository fraudAlertRepository;

    @Mock
    private TransactionMapper transactionMapper;

    @InjectMocks
    private TransactionService transactionService;

    private Customer customer;
    private Account account;
    private Transaction transaction;
    private TransactionRequest request;
    private FraudResult fraudResult;

    @BeforeEach
    void setup(){
        customer = new Customer();
        customer.setId(UUID.randomUUID());

        account = new Account();
        account.setId(UUID.randomUUID());

        transaction = new Transaction();
        transaction.setAmount(new BigDecimal("15000"));

        request = new TransactionRequest();
        request.setAmount(new BigDecimal("15000"));
        request.setCountry("NG");
        request.setCustomerId(customer.getId());
        request.setAccountId(account.getId());

        fraudResult = new FraudResult(90, RiskLevel.HIGH, List.of("High-risk country"), TransactionStatus.REJECTED);



    }

    @Test
    void shouldProcessTransactionAndCreateFraudAlert() {
        when(customerRepository.findById(customer.getId()))
                .thenReturn(Optional.of(customer));

        when(accountRepository.findById(account.getId()))
                .thenReturn(Optional.of(account));

        when(transactionMapper.toEntity(request, customer, account))
                .thenReturn(transaction);

        when(fraudEngine.evaluate(transaction, customer))
                .thenReturn(fraudResult);

        FraudResult result = transactionService.process(request);

        assertNotNull(result);

        verify(transactionRepository).save(transaction);

        verify(fraudAlertRepository).save(any(FraudAlert.class));
    }

}
