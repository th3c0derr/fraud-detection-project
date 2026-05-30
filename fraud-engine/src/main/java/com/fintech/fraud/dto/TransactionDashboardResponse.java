package com.fintech.fraud.dto;

import com.fintech.fraud.enums.RiskLevel;
import com.fintech.fraud.enums.TransactionStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionDashboardResponse {

    private UUID id;

    private BigDecimal amount;

    private String country;

    private String riskLevel;

    private Integer riskScore;

    private String status;

    private LocalDateTime timestamp;

    public TransactionDashboardResponse(UUID id, BigDecimal amount, String country, String riskLevel, Integer riskScore, String status, LocalDateTime timestamp) {
        this.id = id;
        this.amount = amount;
        this.country = country;
        this.riskLevel = riskLevel;
        this.riskScore = riskScore;
        this.status = status;
        this.timestamp = timestamp;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public Integer getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(Integer riskScore) {
        this.riskScore = riskScore;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
