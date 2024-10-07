package com.example.busreservation.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
@Document(collection = "payments")
public class Payment {
    @Id
    private String paymentId;

    @DBRef
    private Booking booking;

    private float amount;
    private String paymentMethod; // e.g. "credit card", "debit card"
    private String status; // e.g. "completed", "pending"
    private String userEmail; // Added to store user email if needed
}
