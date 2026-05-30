package com.fintech.fraud.mapper;

import com.fintech.fraud.dto.TransactionRequest;
import com.fintech.fraud.entity.Account;
import com.fintech.fraud.entity.Customer;
import com.fintech.fraud.entity.Transaction;
import com.fintech.fraud.enums.TransactionStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TransactionMapper {

    public Transaction toEntity(TransactionRequest request, Customer customer, Account account){

            Transaction tx = new Transaction();

            tx.setAmount(request.getAmount());
            tx.setCurrency(request.getCurrency());
            tx.setCountry(request.getCountry());

            tx.setCustomer(customer);
            tx.setAccount(account);

            tx.setTimestamp(LocalDateTime.now());
            tx.setStatus(TransactionStatus.PENDING);

            return tx;
    }
}
