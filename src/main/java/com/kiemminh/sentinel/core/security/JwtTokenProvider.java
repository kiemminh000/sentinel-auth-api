package com.kiemminh.sentinel.core.security;

import com.kiemminh.sentinel.core.util.Constants;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    // Create a Key object from our secret string.
    private final Key key = Keys.hmacShaKeyFor(Constants.JWT_SECRET.getBytes());

    // 1. Generate a token after a successful login.
    public String generateToken(String username, String role) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + Constants.JWT_EXPIRATION);

        return Jwts.builder()
                .setSubject(username)                 // Payload: Store the username.
                .claim("role", role)                  // Payload: Store the role.
                .setIssuedAt(now)                     // Issued at time.
                .setExpiration(expiryDate)            // Expiration time.
                .signWith(key, SignatureAlgorithm.HS256) // Secure signature.
                .compact();
    }

    // 2. Extract the username from the token (used when a user sends a token to call an API).
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    // 3. Check whether the token is valid, tampered with, or expired.
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            System.out.println("Invalid JWT signature");
        } catch (ExpiredJwtException e) {
            System.out.println("Expired JWT token");
        } catch (UnsupportedJwtException e) {
            System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty");
        }
        return false;
    }
}