package com.crio.eventmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crio.eventmanagement.controller.exchange.requests.AddEventRequest;
import com.crio.eventmanagement.controller.exchange.requests.UpdateEventRequest;
import com.crio.eventmanagement.model.Event;
import com.crio.eventmanagement.service.EventService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/events")
@PreAuthorize("hasAuthority('EVENT_MANAGER')")
public class EventController {

    @Autowired
    EventService eventService;


    @GetMapping()
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }
    

    @PostMapping()
	public Event addEvent(@Valid @RequestBody AddEventRequest addEventRequest) {
        return eventService.addEvent(addEventRequest);
	}

    @PutMapping("/{eventId}")
    public Event putMethodName(@PathVariable("eventId") String eventId, @RequestBody UpdateEventRequest updateEventRequest) {
        return eventService.updateEvent(eventId, updateEventRequest);
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Object> deleteEvent(@PathVariable("eventId") String eventId) {
        eventService.deleteEvent(eventId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("{}");
    }

}
