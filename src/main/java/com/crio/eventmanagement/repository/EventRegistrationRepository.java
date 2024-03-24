package com.crio.eventmanagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.crio.eventmanagement.model.EventRegistration;

public interface EventRegistrationRepository extends MongoRepository <EventRegistration, String> {
    
}