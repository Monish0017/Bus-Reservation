package com.example.busreservation.controller;

import com.example.busreservation.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/book")
    public String bookTicket(@RequestBody BookingRequest bookingRequest) {
        return bookingService.bookTicket(bookingRequest.getEmail(), bookingRequest.getBusNo(), bookingRequest.getRequestedSeats());
    }

    // Inner class for booking request data
    public static class BookingRequest {
        private String email;
        private String busNo;
        private int[] requestedSeats;

        // Getters and setters
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getBusNo() {
            return busNo;
        }

        public void setBusNo(String busNo) {
            this.busNo = busNo;
        }

        public int[] getRequestedSeats() {
            return requestedSeats;
        }

        public void setRequestedSeats(int[] requestedSeats) {
            this.requestedSeats = requestedSeats;
        }
    }
}
