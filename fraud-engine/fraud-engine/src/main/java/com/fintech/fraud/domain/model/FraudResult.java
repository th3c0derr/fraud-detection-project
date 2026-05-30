package com.fintech.fraud.domain.model;

import com.fintech.fraud.enums.RiskLevel;
import com.fintech.fraud.enums.TransactionStatus;

import java.util.List;

public class FraudResult {
    private int riskScore;
    private RiskLevel riskLevel;
    private List<String> reasons;
    private TransactionStatus finalStatus;

    public FraudResult(int riskScore, RiskLevel riskLevel, List<String> reasons, TransactionStatus finalStatus) {
        this.riskScore = riskScore;
        this.riskLevel = riskLevel;
        this.reasons = reasons;
        this.finalStatus = finalStatus;
    }

    public boolean isFraud(){
        return finalStatus == TransactionStatus.REJECTED;
    }

    public int getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(int riskScore) {
        this.riskScore = riskScore;
    }

    public RiskLevel getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
    }

    public List<String> getReasons() {
        return reasons;
    }

    public void setReasons(List<String> reasons) {
        this.reasons = reasons;
    }

    public TransactionStatus getFinalStatus() {
        return finalStatus;
    }

    public void setFinalStatus(TransactionStatus finalStatus) {
        this.finalStatus = finalStatus;
    }
}
