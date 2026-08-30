package com.schoolproject.management.utilties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTUtility {

    private final long expirationSeconds;
    private final SecretKey key;

    public JWTUtility(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiry}") long expirationSeconds
    ) {
        this.expirationSeconds = expirationSeconds;
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateJWTToken(String email) {
        return Jwts.builder()
                .signWith(key)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + (1000 * expirationSeconds)))
                .subject(email)
                .compact();
    }

    public String getEmailFromToken(String token) {
        return this.extractClaims(token).getSubject();
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
