package com.crio.eventmanagement.service.implementation;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.crio.eventmanagement.service.JWTService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JWTServiceImpl implements JWTService {

    private final String secretKey = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

    // Generate JWT Token
    @Override
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    @Override
    public String generateToken(Map<String, Object> additionalClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(additionalClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) //ONE DAY
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Validate JWT token
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpirationDate(token).before(new Date());
    }

    // EXTRACT METHODS
    public String extractUserName(String token) {
        // Claims claims = extractAllClaims(token);
        // return claims.getSubject();
        return extractClaims(token, Claims::getSubject);
    }

    public Date extractExpirationDate(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    // To exctract a specific claim
    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
            final Claims claims = extractAllClaims(token);
            return claimsResolver.apply(claims);
    }

    // To extract payload (claims) from the JWT token
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // private SecretKeySpec getSigningKey() {
    // SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(),
    // Jwts.SIG.HS256.toString());
    // return secretKeySpec;
    // }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
