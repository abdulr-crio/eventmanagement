package com.crio.eventmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crio.eventmanagement.controller.exchange.requests.AuthenticationRequest;
import com.crio.eventmanagement.controller.exchange.requests.RegisterRequest;
import com.crio.eventmanagement.controller.exchange.responses.AuthenticationResponse;
import com.crio.eventmanagement.model.User;
import com.crio.eventmanagement.repository.UserRepository;
import com.crio.eventmanagement.service.AuthenticationService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
public class AuthenticationController {

  @Autowired
  AuthenticationService service;

  @Autowired
  UserRepository userRepository;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @Valid @RequestBody RegisterRequest request) {
    return ResponseEntity.ok(service.register(request));
  }

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @Valid @RequestBody AuthenticationRequest request) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  @GetMapping("/users")
  public List<User> getMethodName2() {
    return userRepository.findAll();
  }

}
