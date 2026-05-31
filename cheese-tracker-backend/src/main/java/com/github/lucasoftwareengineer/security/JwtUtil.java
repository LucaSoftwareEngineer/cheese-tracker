package com.github.lucasoftwareengineer.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * Utility per generare e validare i token JWT.
 */
@Component
public class JwtUtil {

    /**
     * Chiave segreta per la firma del token. Viene letta da `application.properties`
     * tramite la proprietà `jwt.secret`. Deve essere almeno 32 caratteri per HS256.
     */
    @Value("${jwt.secret}")
    private String secret;

    /**
     * Durata del token in millisecondi (1 ora). Valore configurabile via `jwt.expirationMs`.
     */
    @Value("${jwt.expirationMs:3600000}")
    private long expirationMs;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    /**
     * Crea un token JWT contenente lo username dell'utente.
     */
    public String generateToken(String username) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expirationMs);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Estrae lo username dal token.
     */
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * Verifica se il token è valido (firma corretta e non scaduto).
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
