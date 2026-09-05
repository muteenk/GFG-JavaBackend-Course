package com.splitwise.splitwise.utilities;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTUtility {

    private final SecretKey secretKey;
    private final long accessExpiry;

    public JWTUtility(
            @Value("${jwt.secret-key}") String secretKey,
            @Value("${jwt.access-expiry}") long accessExpiry
    ) {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.accessExpiry = accessExpiry;
    }

    public String generateJWTToken(String id) {
        return Jwts.builder()
                .signWith(secretKey)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + (1000*accessExpiry)))
                .subject(id)
                .compact();
    }

    public String getUserIdFromToken(String token) {
        Claims claims = extractToken(token);
        return claims.getSubject();
    }

    private Claims extractToken(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
