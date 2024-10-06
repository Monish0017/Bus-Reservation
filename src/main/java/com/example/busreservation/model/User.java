package com.example.busreservation.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String userId; 
    private String username;
    private String email;
    private String password;
    private String phoneNumber; 

    // Constructor for User with all fields
    public User(String userId, String username, String email, String password, String phoneNumber) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    // Constructor for User without userId
    public User(String username, String email, String password, String phoneNumber) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    // Constructor for User with only userId
    public User(String userId) {
        this.userId = userId;
    }

    // Default constructor
    public User() {}
}
