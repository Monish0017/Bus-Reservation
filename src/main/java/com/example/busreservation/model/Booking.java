package com.example.busreservation.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "bookings")
public class Booking {
    @Id
    private String bookingId;
    private User user;
    private Bus bus;
    private Date bookingDate;
    private String status;
    private List<Integer> bookedSeats; // Holds booked seat numbers

    // Default constructor
    public Booking() {}
}
