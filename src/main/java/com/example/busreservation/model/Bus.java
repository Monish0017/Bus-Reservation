package com.example.busreservation.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Data
@Document(collection = "buses")
public class Bus {
    @Id
    private String busId;
    private String busNo;
    private String busType;
    private int totalSeats;
    private List<Integer> availableSeats; // Changed to List<Integer>
    private float price;
    private String departureCity; // Added field
    private String destinationCity; // Added field

    @DBRef
    private Schedule schedule;  // Use DBRef for referencing Schedule
}
