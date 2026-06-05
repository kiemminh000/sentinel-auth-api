package com.kiemminh.sentinel.domain.transaction.service;

import com.kiemminh.sentinel.core.exception.CustomAPIException;
import com.kiemminh.sentinel.domain.transaction.dto.TransactionRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiskEvaluationService {

    // Simulated blacklist database for demonstration
    private final List<String> blacklistedIps = List.of("192.168.1.99", "10.0.0.5");

    /**
     * Evaluates the transaction against security rules.
     * Throws CustomAPIException if rules are violated.
     */
    public String evaluateTransaction(TransactionRequest request) {
        // Rule 1: Prevent negative or zero amounts
        if (request.getAmount() <= 0) {
            throw new CustomAPIException(HttpStatus.BAD_REQUEST, "Transaction amount must be strictly greater than zero");
        }

        // Rule 2: Block transactions from known malicious IPs
        if (blacklistedIps.contains(request.getIpAddress())) {
            throw new CustomAPIException(HttpStatus.FORBIDDEN, "Transaction blocked: Suspicious IP address detected");
        }

        // Rule 3: Hard limit for single transactions (e.g., 500 million)
        if (request.getAmount() > 500000000) {
            throw new CustomAPIException(HttpStatus.FORBIDDEN, "Transaction blocked: Amount exceeds system limits");
        }

        return "Transaction evaluated: CLEAN. Ready for downstream core processing.";
    }
}