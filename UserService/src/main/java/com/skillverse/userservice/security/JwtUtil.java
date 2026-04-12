package com.skillverse.userservice.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {

    // 🔹 Secret key (move to config later)
    private static final String SECRET_KEY = "my-secret-key-my-secret-key-my-secret-key";

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // 🔹 Token validity (1 day)
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    // 🔹 Generate token
    public String generateToken(String username, List<String> authorities) {

        return Jwts.builder()
                .setSubject(username)
                .claim("authorities", authorities)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // 🔹 Extract username
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // 🔹 Extract authorities
    public List<String> extractAuthorities(String token) {
        return extractAllClaims(token).get("authorities", List.class);
    }

    // 🔹 Validate token
    public boolean isTokenValid(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }

    // 🔹 Check expiration
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    // 🔹 Extract all claims
    private Claims extractAllClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}