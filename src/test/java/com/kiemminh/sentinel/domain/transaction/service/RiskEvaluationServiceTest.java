package com.kiemminh.sentinel.domain.transaction.service;

import com.kiemminh.sentinel.core.exception.CustomAPIException;
import com.kiemminh.sentinel.domain.transaction.dto.TransactionRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class RiskEvaluationServiceTest {

    private RiskEvaluationService riskService;

    // This method runs before each test case to initialize a clean Service instance
    @BeforeEach
    void setUp() {
        riskService = new RiskEvaluationService();
    }

    @Test
    void evaluateTransaction_ShouldReturnSuccess_WhenDataIsValid() {
        // Arrange: prepare valid input data
        TransactionRequest request = new TransactionRequest();
        request.setAmount(100000);
        request.setIpAddress("192.168.1.100"); // safe IP

        // Act: call the method under test
        String result = riskService.evaluateTransaction(request);

        // Assert: verify the result matches expectation
        assertEquals("Transaction evaluated: CLEAN. Ready for downstream core processing.", result);
    }

    @Test
    void evaluateTransaction_ShouldThrowException_WhenAmountIsNegative() {
        // Arrange: intentionally set a negative amount
        TransactionRequest request = new TransactionRequest();
        request.setAmount(-50000);
        request.setIpAddress("192.168.1.100");

        // Act & Assert: should throw CustomAPIException with status 400
        CustomAPIException exception = assertThrows(CustomAPIException.class, () -> {
            riskService.evaluateTransaction(request);
        });

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        assertEquals("Transaction amount must be strictly greater than zero", exception.getMessage());
    }

    @Test
    void evaluateTransaction_ShouldThrowException_WhenIpIsBlacklisted() {
        // Arrange: intentionally use a blacklisted IP (as defined in the service)
        TransactionRequest request = new TransactionRequest();
        request.setAmount(100000);
        request.setIpAddress("10.0.0.5"); // blocked IP

        // Act & Assert: should throw 403 Forbidden error
        CustomAPIException exception = assertThrows(CustomAPIException.class, () -> {
            riskService.evaluateTransaction(request);
        });

        assertEquals(HttpStatus.FORBIDDEN, exception.getStatus());
        assertTrue(exception.getMessage().contains("Suspicious IP address"));
    }
}