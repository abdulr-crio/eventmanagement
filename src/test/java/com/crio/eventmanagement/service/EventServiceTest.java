package com.crio.eventmanagement.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.crio.eventmanagement.controller.exchange.requests.AddEventRequest;
import com.crio.eventmanagement.model.Event;
import com.crio.eventmanagement.repository.EventRegistrationRepository;
import com.crio.eventmanagement.repository.EventRepository;
import com.crio.eventmanagement.repository.UserRepository;
import com.crio.eventmanagement.service.implementation.EventServiceImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EventRegistrationRepository eventRegistrationRepository;

    @Mock
    private MailService mailService;

    @InjectMocks
    private EventServiceImpl eventService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddEvent() {
        // Prepare test data
        AddEventRequest addEventRequest = new AddEventRequest();
        addEventRequest.setEventName("Test Event");
        addEventRequest.setEventDescription("Description");
        addEventRequest.setEventPlace("Place");
        addEventRequest.setEventDate(LocalDate.now());
        addEventRequest.setEventTime(LocalTime.now());
        addEventRequest.setRegisteredUsers(new ArrayList<>());

        Event event = Event.builder()
                .eventName("Test Event")
                .eventDescription("Description")
                .eventPlace("Place")
                .eventDate(LocalDate.now())
                .eventTime(LocalTime.now())
                .registeredUsers(new ArrayList<>())
                .build();

        // Mocking behavior
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        // Calling the service method
        Event result = eventService.addEvent(addEventRequest);

        // Verifying the result
        assertNotNull(result);
        assertEquals(event.getEventName(), result.getEventName());
        assertEquals(event.getEventDescription(), result.getEventDescription());
        assertEquals(event.getEventPlace(), result.getEventPlace());
        assertEquals(event.getEventDate(), result.getEventDate());
        assertEquals(event.getEventTime(), result.getEventTime());
        verify(eventRepository, times(1)).save(any(Event.class));
    }

    @Test
    public void testGetAllEvents() {
        // Prepare test data
        List<Event> events = new ArrayList<>();
        events.add(new Event("1", "Event 1", "Description 1", "Place 1", LocalDate.now(), LocalTime.now(), new ArrayList<>()));
        events.add(new Event("2", "Event 2", "Description 2", "Place 2", LocalDate.now(), LocalTime.now(), new ArrayList<>()));

        // Mocking behavior
        when(eventRepository.findAll()).thenReturn(events);

        // Calling the service method
        List<Event> result = eventService.getAllEvents();

        // Verifying the result
        assertNotNull(result);
        assertEquals(events.size(), result.size());
        assertEquals(events, result);
        verify(eventRepository, times(1)).findAll();
    }

}
