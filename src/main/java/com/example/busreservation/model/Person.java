package com.example.busreservation.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "persons")
public abstract class Person {
    @Id
    private String id;  // Use String type for MongoDB ObjectId
    private String name;
    private String email;
    private String password; // Hashed password
}
