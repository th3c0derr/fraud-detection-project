package com.fintech.fraud.repository;

import com.fintech.fraud.entity.FraudEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FraudEvaluationRepository extends JpaRepository<FraudEvaluation, UUID> {
}
