package com.kiemminh.sentinel.domain.transaction.controller;

import com.kiemminh.sentinel.domain.transaction.dto.TransactionRequest;
import com.kiemminh.sentinel.domain.transaction.service.RiskEvaluationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionFilterController {

    private final RiskEvaluationService riskService;

    public TransactionFilterController(RiskEvaluationService riskService) {
        this.riskService = riskService;
    }

    /**
     * Secured endpoint. Requires Bearer JWT Token.
     */
    @PostMapping("/evaluate")
    public ResponseEntity<String> evaluate(@RequestBody TransactionRequest request) {
        String evaluationResult = riskService.evaluateTransaction(request);
        return ResponseEntity.ok(evaluationResult);
    }
}