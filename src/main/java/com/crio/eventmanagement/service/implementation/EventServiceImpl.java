package com.crio.eventmanagement.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crio.eventmanagement.controller.exchange.requests.AddEventRequest;
import com.crio.eventmanagement.exception.ServerException;
import com.crio.eventmanagement.model.Event;
import com.crio.eventmanagement.repository.EventRepository;
import com.crio.eventmanagement.service.EventService;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository eventRepository;

    @Override
    public Event addEvent(AddEventRequest addEventRequest) {
        addEventRequest.setRegisteredUsers(new ArrayList<>());
        Event event = Event.builder()
        .eventName(addEventRequest.getEventName())
        .eventDescription(addEventRequest.getEventDescription())
        .eventDate(addEventRequest.getEventDate())
        .eventTime(addEventRequest.getEventTime())
        .registeredUsers(addEventRequest.getRegisteredUsers())
        .build();

        if(event != null){
        return eventRepository.save(event);
    }
    throw new ServerException("Server Error");
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    } 
    
}
