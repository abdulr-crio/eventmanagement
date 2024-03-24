package com.crio.eventmanagement.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.crio.eventmanagement.controller.exchange.responses.RegisteredEventsResponse;

public interface UserService extends UserDetailsService {
    List<RegisteredEventsResponse> findAllRegisteredEvents();
}
