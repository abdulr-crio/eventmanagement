package com.crio.eventmanagement.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.crio.eventmanagement.controller.exchange.requests.AddEventRequest;
import com.crio.eventmanagement.model.Event;
import com.crio.eventmanagement.service.EventService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EventControllerTest {

    @Mock
    private EventService eventService;

    @InjectMocks
    private EventController eventController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllEvents() {
        // Mocking eventService.getAllEvents()
        List<Event> events = Arrays.asList(new Event(), new Event());
        when(eventService.getAllEvents()).thenReturn(events);

        // Calling controller method
        List<Event> result = eventController.getAllEvents();

        // Verifying the response
        assertEquals(events.size(), result.size());
        assertEquals(events, result);
        verify(eventService, times(1)).getAllEvents();
    }

    @Test
    public void testAddEvent() {
        // Mocking eventService.addEvent()
        AddEventRequest addEventRequest = new AddEventRequest();
        Event event = Event.builder()
                .id("1")
                .eventName("Test Event")
                .eventDescription("Description")
                .eventPlace("Place")
                .eventDate(LocalDate.now())
                .eventTime(LocalTime.now())
                .build();
        when(eventService.addEvent(addEventRequest)).thenReturn(event);

        // Calling controller method
        Event result = eventController.addEvent(addEventRequest);

        // Verifying the response
        assertEquals(event, result);
        verify(eventService, times(1)).addEvent(addEventRequest);
    }

}
