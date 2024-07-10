package com.sparta.spartabackoffice.admin.jwtutil;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration-time}")
    private long expirationTime;

    private byte[] secretKeyBytes;

    @PostConstruct
    protected void init() {
        secretKeyBytes = Base64.getEncoder().encode(secret.getBytes());
    }

    public String generateToken(String email) {
        logger.debug("Generating token for email: {}", email);
        String token = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS512, secretKeyBytes)
                .compact();
        logger.debug("Generated token: {}", token);
        return token;
    }

    public Claims extractClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKeyBytes).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            logger.error("Token expired: {}", e.getMessage());
            throw e;
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            logger.error("Invalid token: {}", e.getMessage());
            throw new JwtException("Invalid token", e);
        }
    }

    public Boolean validateToken(String token, String email) {
        try {
            final String tokenEmail = extractClaims(token).getSubject();
            boolean isValid = tokenEmail.equals(email) && !isTokenExpired(token);
            logger.debug("Token validation result for email {}: {}", email, isValid);
            return isValid;
        } catch (JwtException e) {
            logger.error("Token validation failed: {}", e.getMessage());
            return false;
        }
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = extractClaims(token).getExpiration();
        return expiration.before(new Date());
    }
}