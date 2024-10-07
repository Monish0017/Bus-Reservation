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
    private List<Integer> availableSeats;
    private float price;
    private String departureCity;
    private String destinationCity;
    private String departureTime;   // Changed to String
    private String arrivalTime;     // Changed to String

    @DBRef
    private Schedule schedule;  // For reference to a Schedule model, if needed
}
