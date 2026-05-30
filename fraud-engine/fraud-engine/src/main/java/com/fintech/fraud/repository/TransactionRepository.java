package com.fintech.fraud.repository;

import com.fintech.fraud.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepository
    extends JpaRepository<Transaction, UUID> {

}
