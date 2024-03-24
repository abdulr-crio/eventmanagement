package com.crio.eventmanagement.service;

import java.util.List;

import com.crio.eventmanagement.controller.exchange.requests.AddEventRequest;
import com.crio.eventmanagement.controller.exchange.requests.UpdateEventRequest;
import com.crio.eventmanagement.model.Event;

public interface EventService {
    Event addEvent(AddEventRequest addEventRequest);
    List <Event> getAllEvents();
    Event updateEvent (String eventId, UpdateEventRequest updateEventRequest);
    void deleteEvent (String eventId);
}
