package com.crio.eventmanagement.controller.exchange.responses;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
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
    private String eventPlace;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate eventDate;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime eventTime;
}
