package com.kiemminh.sentinel.domain.transaction.dto;

public class TransactionRequest {
    private String transactionId;
    private Double amount;

    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
}
