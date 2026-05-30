package com.fintech.fraud.dto;

import com.fintech.fraud.domain.model.FraudResult;

import java.util.UUID;

//What comes out through the API
public class TransactionResponse {
    private UUID transactionId;
    private FraudResult fraudResult;
}
