package com.crio.eventmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crio.eventmanagement.controller.exchange.responses.RegisteredEventsResponse;
import com.crio.eventmanagement.service.UserService;

@RestController
@PreAuthorize("hasAuthority('USER')")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/registrations")
    public List<RegisteredEventsResponse> findAllRegistedEvents(){
        return userService.findAllRegisteredEvents();
    }
}
