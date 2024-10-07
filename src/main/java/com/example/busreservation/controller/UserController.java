package com.example.busreservation.controller;

import com.example.busreservation.model.User;
import com.example.busreservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return userService.registerUser(user);  // Directly return the ResponseEntity from the service
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Validated LoginRequest loginRequest) {
        return userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());  // Directly return ResponseEntity
    }

    public static class LoginRequest {
        private String email;
        private String password;

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
