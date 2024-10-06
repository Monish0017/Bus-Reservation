package com.example.busreservation.service;

import com.example.busreservation.model.User;
import com.example.busreservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Simple hashing function for password
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // Register a new user
    public String registerUser(User user) {
        // Check if the email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return "Email already exists!"; // Change message to reflect email check
        }
        user.setPassword(hashPassword(user.getPassword())); // Hash the password
        userRepository.save(user);
        return "User registered successfully!";
    }

    // Login user
    public String loginUser(String email, String password) { // Change parameter from username to email
        User user = userRepository.findByEmail(email).orElse(null); // Change method call to findByEmail
        if (user != null) {
            // Compare the hashed passwords
            if (user.getPassword().equals(hashPassword(password))) {
                return "Login successful!";
            } else {
                return "Invalid password!";
            }
        }
        return "User not found!";
    }
}
