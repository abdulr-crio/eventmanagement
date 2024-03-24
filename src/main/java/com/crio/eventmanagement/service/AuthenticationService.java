package com.crio.eventmanagement.service;

import com.crio.eventmanagement.controller.exchange.requests.AuthenticationRequest;
import com.crio.eventmanagement.controller.exchange.requests.RegisterRequest;
import com.crio.eventmanagement.controller.exchange.responses.AuthenticationResponse;

public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse login(AuthenticationRequest request);

}