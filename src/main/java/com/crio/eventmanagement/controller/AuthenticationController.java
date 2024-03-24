package com.crio.eventmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crio.eventmanagement.controller.exchange.requests.AuthenticationRequest;
import com.crio.eventmanagement.controller.exchange.requests.RegisterRequest;
import com.crio.eventmanagement.controller.exchange.responses.AuthenticationResponse;
import com.crio.eventmanagement.service.AuthenticationService;

import jakarta.validation.Valid;

@RestController
public class AuthenticationController {

  @Autowired
  AuthenticationService authenticationService;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @Valid @RequestBody RegisterRequest request) {
    return ResponseEntity.ok(authenticationService.register(request));
  }

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @Valid @RequestBody AuthenticationRequest request) {
    return ResponseEntity.ok(authenticationService.login(request));
  }
}
