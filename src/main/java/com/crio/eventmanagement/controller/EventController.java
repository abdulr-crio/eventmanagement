package com.crio.eventmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crio.eventmanagement.controller.exchange.requests.AddEventRequest;
import com.crio.eventmanagement.model.Event;
import com.crio.eventmanagement.service.EventService;

import jakarta.validation.Valid;


@RestController
@PreAuthorize("hasAuthority('EVENT_MANAGER')")
public class EventController {

    @Autowired
    EventService eventService;


    @GetMapping("/events")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }
    

    @PostMapping("/events")
	public Event addEvent(@Valid @RequestBody AddEventRequest addEventRequest) {
        return eventService.addEvent(addEventRequest);
	}
    
}
