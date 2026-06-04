package com.kiemminh.sentinel.core.util;

public class Constants {
    // Secret key used to sign JWTs. In production, this key should be at least 256 bits long and stored in an environment variable.
    public static final String JWT_SECRET = "DayLaMotChiecChiaKhoaBiMatRatDaiVaSieuBaoMatChoDuAnSentinelCuaToi123456789";
    
    // Token lifetime: 24 hours (in milliseconds)
    public static final long JWT_EXPIRATION = 86400000; 
}