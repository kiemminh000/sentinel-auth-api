package com.kiemminh.sentinel.domain.auth.dto;

public class TokenResponse {
    private String token;
    private String type = "Bearer";
    private String role;

    public TokenResponse(String token, String role) {
        this.token = token;
        this.role = role;
    }

    // Getters and Setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}