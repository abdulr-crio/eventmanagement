package com.crio.eventmanagement.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.crio.eventmanagement.controller.exchange.requests.AuthenticationRequest;
import com.crio.eventmanagement.controller.exchange.requests.RegisterRequest;
import com.crio.eventmanagement.controller.exchange.responses.AuthenticationResponse;
import com.crio.eventmanagement.service.AuthenticationService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuthenticationControllerTest {

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegister() {
        // Prepare test data
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("test@example.com");
        registerRequest.setPassword("password");

        // Mocking behavior
        AuthenticationResponse expectedResponse = new AuthenticationResponse();
        expectedResponse.setAccessToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtdW56eWVkQGdtYWlsLmNvbSIsImlhdCI6MTcxMTI5ODMzMiwiZXhwIjoxNzExMzg0NzMyfQ.BeLMNpQNvaxlVj41FFQdSarP8oeA1nbeoPkcxuLkzbs");
        when(authenticationService.register(any(RegisterRequest.class))).thenReturn(expectedResponse);

        // Calling the controller method
        ResponseEntity<AuthenticationResponse> responseEntity = authenticationController.register(registerRequest);

        // Verifying the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        AuthenticationResponse responseBody = responseEntity.getBody();
        assertEquals("Success", responseBody.getMessage());
        assertNotNull(responseBody.getAccessToken());

        // You can decode and verify the JWT token here if needed
        // For simplicity, we're just checking if it's not null
        assertTrue(responseBody.getAccessToken().startsWith("eyJ")); // JWT token starts with "eyJ"
        verify(authenticationService, times(1)).register(any(RegisterRequest.class));
    }

    @Test
    public void testAuthenticate() {
        // Prepare test data
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setEmail("test@example.com");
        authenticationRequest.setPassword("password");

        // Mocking behavior
        AuthenticationResponse expectedResponse = new AuthenticationResponse();
        expectedResponse.setAccessToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtdW56eWVkQGdtYWlsLmNvbSIsImlhdCI6MTcxMTI5ODMzMiwiZXhwIjoxNzExMzg0NzMyfQ.BeLMNpQNvaxlVj41FFQdSarP8oeA1nbeoPkcxuLkzbs");
        when(authenticationService.login(any(AuthenticationRequest.class))).thenReturn(expectedResponse);

        // Calling the controller method
        ResponseEntity<AuthenticationResponse> responseEntity = authenticationController.authenticate(authenticationRequest);

        // Verifying the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        AuthenticationResponse responseBody = responseEntity.getBody();
        assertEquals("Success", responseBody.getMessage());
        assertNotNull(responseBody.getAccessToken());

        // You can decode and verify the JWT token here if needed
        // For simplicity, we're just checking if it's not null
        assertTrue(responseBody.getAccessToken().startsWith("eyJ")); // JWT token starts with "eyJ"
        verify(authenticationService, times(1)).login(any(AuthenticationRequest.class));
    }
}