package com.crio.eventmanagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.crio.eventmanagement.model.Event;

public interface EventRepository extends MongoRepository<Event, String> {
    
}
