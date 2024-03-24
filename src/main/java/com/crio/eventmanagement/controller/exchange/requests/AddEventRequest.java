package com.crio.eventmanagement.controller.exchange.requests;

import java.util.Date;
import java.util.List;

import com.crio.eventmanagement.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddEventRequest {
    @NotBlank private String eventName;
    @NotBlank private String eventDescription;
    @JsonFormat(pattern="dd-MM-yyyy")
    @NotNull private Date eventDate;
    @JsonFormat(pattern = "HH:mm")
    @NotNull private Date eventTime;
    private List<User> registeredUsers;
}
