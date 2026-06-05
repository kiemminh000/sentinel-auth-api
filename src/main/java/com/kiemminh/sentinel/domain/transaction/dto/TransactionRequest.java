package com.kiemminh.sentinel.domain.transaction.dto;

public class TransactionRequest {
    private String fromAccount;
    private String toAccount;
    private double amount;
    private String ipAddress;

    // Constructors
    public TransactionRequest() {}

    // Getters and Setters
    public String getFromAccount() { return fromAccount; }
    public void setFromAccount(String fromAccount) { this.fromAccount = fromAccount; }

    public String getToAccount() { return toAccount; }
    public void setToAccount(String toAccount) { this.toAccount = toAccount; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
}