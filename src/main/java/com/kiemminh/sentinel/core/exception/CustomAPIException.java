package com.kiemminh.sentinel.core.exception;

public class CustomAPIException extends RuntimeException {
    private int status;

    public CustomAPIException(int status, String message) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
