package com.crio.eventmanagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.crio.eventmanagement.model.User;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    
    Optional<User> findByEmail(String email);
    
}
