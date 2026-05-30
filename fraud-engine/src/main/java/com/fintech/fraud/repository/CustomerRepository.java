package com.fintech.fraud.repository;

import com.fintech.fraud.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository
        extends JpaRepository<Customer, UUID> {
    boolean existsByEmail(String email);
}
