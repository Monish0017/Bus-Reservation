package com.example.busreservation.controller;

import com.example.busreservation.model.User;
import com.example.busreservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("API is up and running!");
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Validated User user) {
        try {
            String message = userService.registerUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        } catch (Exception e) {
            // Handle the exception and return a meaningful message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error registering user: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Validated LoginRequest loginRequest) {
        try {
            String token = userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
            if (token != null) {
                return ResponseEntity.ok(token); // Return token if login is successful
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } catch (Exception e) {
            // Handle exceptions during login
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error logging in: " + e.getMessage());
        }
    }

    // Define a new static inner class or a separate DTO class for login requests
    public static class LoginRequest {
        private String email;
        private String password;

        // Getters and setters
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
