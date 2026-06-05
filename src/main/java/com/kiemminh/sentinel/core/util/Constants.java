package com.kiemminh.sentinel.core.util;

public class Constants {
    // JWT configuration constants
    public static final String JWT_SECRET = System.getenv("JWT_SECRET") != null 
            ? System.getenv("JWT_SECRET") 
            : "DefaultSecretKeyThatShouldBeVeryLongAndSecureForLocalDev123";
    
    public static final long JWT_EXPIRATION = 86400000; 
}