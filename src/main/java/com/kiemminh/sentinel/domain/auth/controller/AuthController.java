package com.kiemminh.sentinel.domain.auth.controller;

import com.kiemminh.sentinel.domain.auth.dto.LoginRequest;
import com.kiemminh.sentinel.domain.auth.dto.TokenResponse;
import com.kiemminh.sentinel.domain.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
        // Call the service to process the request and return a standard HTTP 200 OK JSON
        TokenResponse token = authService.authenticateUser(loginRequest);
        return ResponseEntity.ok(token);
    }
}