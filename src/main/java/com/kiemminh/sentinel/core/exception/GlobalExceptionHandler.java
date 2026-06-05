package com.kiemminh.sentinel.core.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Centralized exception handler to intercept errors and format them into standardized JSON responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomAPIException.class)
    public ResponseEntity<Map<String, Object>> handleCustomAPIException(CustomAPIException ex) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", LocalDateTime.now());
        errorDetails.put("status", ex.getStatus().value());
        errorDetails.put("error", ex.getMessage());

        return new ResponseEntity<>(errorDetails, ex.getStatus());
    }
}