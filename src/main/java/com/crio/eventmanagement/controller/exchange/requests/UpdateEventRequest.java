package com.crio.eventmanagement.controller.exchange.requests;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEventRequest {
    private String eventName;
    private String eventDescription;
    private String eventPlace;
    
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate eventDate;
    
    @JsonFormat(pattern = "HH:mm")
    private LocalTime eventTime;
}

