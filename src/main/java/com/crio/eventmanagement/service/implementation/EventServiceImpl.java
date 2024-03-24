package com.crio.eventmanagement.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.crio.eventmanagement.controller.exchange.requests.AddEventRequest;
import com.crio.eventmanagement.controller.exchange.requests.UpdateEventRequest;
import com.crio.eventmanagement.exception.AlreadyRegisteredException;
import com.crio.eventmanagement.exception.EventDoesNotExistException;
import com.crio.eventmanagement.exception.ServerException;
import com.crio.eventmanagement.model.Event;
import com.crio.eventmanagement.model.EventRegistration;
import com.crio.eventmanagement.model.User;
import com.crio.eventmanagement.repository.EventRegistrationRepository;
import com.crio.eventmanagement.repository.EventRepository;
import com.crio.eventmanagement.repository.UserRepository;
import com.crio.eventmanagement.service.EventService;
import com.crio.eventmanagement.service.MailService;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EventRegistrationRepository eventRegistrationRepository;

    @Autowired
    MailService mailService;
    

    @Override
    public Event addEvent(AddEventRequest addEventRequest) {
        addEventRequest.setRegisteredUsers(new ArrayList<>());
        Event event = Event.builder()
                .eventName(addEventRequest.getEventName())
                .eventDescription(addEventRequest.getEventDescription())
                .eventPlace(addEventRequest.getEventPlace())
                .eventPlace(addEventRequest.getEventPlace())
                .eventDate(addEventRequest.getEventDate())
                .eventTime(addEventRequest.getEventTime())
                .registeredUsers(addEventRequest.getRegisteredUsers())
                .build();

        if (event != null) {
            return eventRepository.save(event);
        }
        throw new ServerException("Server Error");
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event updateEvent(String eventId, UpdateEventRequest updateEventRequest) {
        Event existingEvent = eventRepository.findById(eventId).orElseThrow(() -> new EventDoesNotExistException("Event does not exist"));

        // Update the fields of the existing event with non-null values from the
        // provided event
        if (updateEventRequest.getEventName() != null) {
            existingEvent.setEventName(updateEventRequest.getEventName());
        }
        if (updateEventRequest.getEventDescription() != null) {
            existingEvent.setEventDescription(updateEventRequest.getEventDescription());
        }
        if (updateEventRequest.getEventDate() != null) {
            existingEvent.setEventDate(updateEventRequest.getEventDate());
        }
        if (updateEventRequest.getEventTime() != null) {
            existingEvent.setEventTime(updateEventRequest.getEventTime());
        }
        if (updateEventRequest.getEventPlace() != null) {
            existingEvent.setEventPlace(updateEventRequest.getEventPlace());
        }

        // Save the updated event to the database
        return eventRepository.save(existingEvent);
    }

    @Override
    public void deleteEvent(String eventId) {
        if(eventId == null){
            throw new EventDoesNotExistException("Event does not exist");
        }
        eventRepository.findById(eventId).orElseThrow(() -> new EventDoesNotExistException("Event does not exist"));
        eventRepository.deleteById(eventId);
    }


    @Override
    public EventRegistration registerForEvent(String eventId) {
        
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
        String email = userDetails.getUsername();      

        
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventDoesNotExistException("Event does not exist"));
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if(event.getRegisteredUsers().contains(user)){
            throw new AlreadyRegisteredException("User already registered");
        }

        event.getRegisteredUsers().add(user);

        EventRegistration eventRegistration = EventRegistration.builder()
        .eventId(event.getId()).email(user.getEmail()).build();

        eventRepository.save(event);

        mailService.sendMail(user.getEmail(), user.getFirstName(), event);
        
        return eventRegistrationRepository.save(eventRegistration);

    }

    @Override
    public void deRegisterForEvent(String eventId) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
        String email = userDetails.getUsername();      
        
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventDoesNotExistException("Event does not exist"));
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        event.getRegisteredUsers().remove(user);
        eventRepository.save(event);

        eventRegistrationRepository.deleteByEmailAndEventId(user.getEmail(), event.getId());
    }
}