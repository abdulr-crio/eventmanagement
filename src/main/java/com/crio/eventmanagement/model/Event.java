package com.crio.eventmanagement.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "events")
public class Event {
    @Id
    private String id;
    private String eventName;
    private String eventDescription;
    private String eventPlace;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate eventDate;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime eventTime;
    // This design can be improved
    @DBRef
    private List<User> registeredUsers;
}
