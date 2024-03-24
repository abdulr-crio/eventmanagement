package com.crio.eventmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class EventmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventmanagementApplication.class, args);
	}

	@GetMapping("/")
	public String welcome() {
		return "Welcome to Event Management API Service!";
	}

	@GetMapping("/event-managers")
	@PreAuthorize("hasAuthority('EVENT_MANAGER')")
	public String welcomeEventManagers() {
		
		return "Welcome to Event Management API Service, Event Managers!";

	}
	

}
