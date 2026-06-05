package com.kiemminh.sentinel.core.exception;

import org.springframework.http.HttpStatus;

/**
 * Custom runtime exception to handle domain-specific business errors.
 */
public class CustomAPIException extends RuntimeException {
    private final HttpStatus status;
    private final String message;

    public CustomAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() { return status; }
    
    @Override
    public String getMessage() { return message; }
}