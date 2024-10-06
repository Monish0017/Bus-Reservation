package com.example.busreservation.repository;

import com.example.busreservation.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email); // Return Optional<User>
    Optional<User> findByUsername(String username); // Keep this if you still want username search
}
