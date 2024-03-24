package com.crio.eventmanagement.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.Date;

/**
 * JWTService
 */
public interface JWTService {

    String generateToken(Map<String, Object> additionalClaims, UserDetails userDetails);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);

    String extractUserName(String token);

    Date extractExpirationDate(String token);
}