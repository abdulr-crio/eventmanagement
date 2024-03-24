package com.crio.eventmanagement.controller.exchange.requests;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEventRequest {
    private String eventName;
    private String eventDescription;
    
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date eventDate;
    
    @JsonFormat(pattern = "HH:mm")
    private Date eventTime;
}

