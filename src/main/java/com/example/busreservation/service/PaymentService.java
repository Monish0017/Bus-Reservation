package com.example.busreservation.service;

import com.example.busreservation.model.Payment;
import com.example.busreservation.model.User;
import com.example.busreservation.repository.PaymentRepository;
import com.example.busreservation.repository.UserRepository; // Import your UserRepository

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository; // Inject UserRepository

    public String processPayment(float amount, String paymentMethod, String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        // Check if the user exists by email
        if (optionalUser.isPresent()) { // Adjust according to your method to find user by email
            // Proceed with payment processing
            Payment payment = new Payment();
            payment.setAmount(amount);
            payment.setPaymentMethod(paymentMethod);
            payment.setStatus("completed");

            paymentRepository.save(payment);
            return "Payment processed successfully";
        }

        System.out.println("User not found. Please check the email address.");
        return "User not found. Please check the email address.";

    }
}
