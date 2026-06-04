package com.kiemminh.sentinel.domain.auth.service;

import com.kiemminh.sentinel.core.security.JwtTokenProvider;
import com.kiemminh.sentinel.domain.auth.dto.LoginRequest;
import com.kiemminh.sentinel.domain.auth.dto.TokenResponse;
import com.kiemminh.sentinel.domain.user.entity.UserInfo;
import com.kiemminh.sentinel.domain.user.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserRepository userRepository;

    public AuthService(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userRepository = userRepository;
    }

    public TokenResponse authenticateUser(LoginRequest loginRequest) {
        // 1. Delegate username and password authentication to Spring Security
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        // 2. If authentication succeeds, load the User from the DB to get the role
        UserInfo user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 3. Generate the token
        String jwt = tokenProvider.generateToken(user.getUsername(), user.getRole());

        // 4. Return the token response
        return new TokenResponse(jwt, user.getRole());
    }
}