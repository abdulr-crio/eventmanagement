package com.crio.eventmanagement.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crio.eventmanagement.model.Event;

@RestController
@PreAuthorize("hasAuthority('EVENT_MANAGER')")
public class EventController {

    @PostMapping("/events")
	public Event welcomeEventManagers(@RequestBody Event event) {
		
		return event;

	}
    
}
