package com.crio.eventmanagement.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crio.eventmanagement.controller.exchange.requests.AuthenticationRequest;
import com.crio.eventmanagement.controller.exchange.requests.RegisterRequest;
import com.crio.eventmanagement.controller.exchange.responses.AuthenticationResponse;
import com.crio.eventmanagement.exception.UserAlreadyExistsException;
import com.crio.eventmanagement.model.User;
import com.crio.eventmanagement.model.enums.Role;
import com.crio.eventmanagement.repository.UserRepository;
import com.crio.eventmanagement.service.AuthenticationService;
import com.crio.eventmanagement.service.JWTService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {
    if(request.getRole() == null){
        request.setRole(Role.USER);
    }
    // Check if user with the same email exists
   if(!(userRepository
   .findByEmail(request.getEmail()).isEmpty())) {
    throw new UserAlreadyExistsException(
        "User with the given email address already exists");
   }

    User user = User.builder()
        .firstName(request.getFirstName())
        .lastName(request.getLastName())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(request.getRole())
        .build();
    
    String jwtToken = jwtService.generateToken(user);
    userRepository.save(user);
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
        .build();
  }

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

}
