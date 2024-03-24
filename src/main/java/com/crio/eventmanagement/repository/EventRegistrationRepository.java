package com.crio.eventmanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.crio.eventmanagement.model.EventRegistration;

public interface EventRegistrationRepository extends MongoRepository <EventRegistration, String> {

    void deleteByEmailAndEventId(String email, String eventId);
    List<EventRegistration> findByEmail(String email);
    Optional<EventRegistration> findByEmailAndEventId(String email, String eventId);
    
}