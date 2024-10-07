package com.example.busreservation.service;

import com.example.busreservation.model.User;
import com.example.busreservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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

    public ResponseEntity<String> registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return new ResponseEntity<>("Email already exists!", HttpStatus.CONFLICT);  // Invalid registration response
        }
        user.setPassword(hashPassword(user.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<>("User registered successfully!", HttpStatus.CREATED);  // Successful registration
    }

    public ResponseEntity<String> loginUser(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getPassword().equals(hashPassword(password))) {
                return new ResponseEntity<>("Login successful!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Invalid password!", HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);
    }
}
