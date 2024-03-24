package com.crio.eventmanagement.service.implementation;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.crio.eventmanagement.controller.exchange.responses.RegisteredEventsResponse;
import com.crio.eventmanagement.model.Event;
import com.crio.eventmanagement.model.EventRegistration;
import com.crio.eventmanagement.model.User;
import com.crio.eventmanagement.repository.EventRegistrationRepository;
import com.crio.eventmanagement.repository.EventRepository;
import com.crio.eventmanagement.repository.UserRepository;
import com.crio.eventmanagement.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EventRegistrationRepository eventRegistrationRepository;

    @Autowired
    EventRepository eventRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public List<RegisteredEventsResponse> findAllRegisteredEvents() {
        
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
        String email = userDetails.getUsername();

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<EventRegistration> registrations = eventRegistrationRepository.findByEmail(user.getEmail());

        List<String> eventIds = registrations.stream().map(EventRegistration::getEventId).collect(Collectors.toList());

        List<Event> events = eventRepository.findAllById(eventIds);

        return events.stream().map(event -> {
            return RegisteredEventsResponse.builder()
            .eventName(event.getEventName())
            .eventDescription(event.getEventDescription())
            .eventPlace(event.getEventPlace())
            .eventDate(event.getEventDate())
            .eventTime(event.getEventTime())
            .build();
        }).collect(Collectors.toList());
    }
    
}
