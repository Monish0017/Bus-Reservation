package com.example.busreservation.controller;

import com.example.busreservation.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/process")
    public String processPayment(@RequestBody PaymentRequest paymentRequest) {
        return paymentService.processPayment(paymentRequest.getAmount(), paymentRequest.getPaymentMethod(),
                paymentRequest.getEmail());
    }

    // Inner class to represent the payment request body
    public static class PaymentRequest {
        private float amount;
        private String paymentMethod;
        private String email; // Add the email field

        // Getters and setters
        public float getAmount() {
            return amount;
        }

        public void setAmount(float amount) {
            this.amount = amount;
        }

        public String getPaymentMethod() {
            return paymentMethod;
        }

        public void setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
        }

        public String getEmail() { // Getter for email
            return email;
        }

        public void setEmail(String email) { // Setter for email
            this.email = email;
        }
    }
}
