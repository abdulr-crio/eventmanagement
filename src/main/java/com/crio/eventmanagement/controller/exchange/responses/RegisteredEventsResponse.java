package com.crio.eventmanagement.controller.exchange.responses;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisteredEventsResponse {
    private String eventName;
    private String eventDescription;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date eventDate;
    @JsonFormat(pattern = "HH:mm")
    private Date eventTime;
}
