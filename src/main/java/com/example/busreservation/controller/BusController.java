package com.example.busreservation.controller;

import com.example.busreservation.model.Bus;
import com.example.busreservation.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buses")
@CrossOrigin(origins = "http://localhost:3000") // Allow CORS for the frontend
public class BusController {

    @Autowired
    private BusService busService;

    // Fetch all buses
    @GetMapping
    public ResponseEntity<List<Bus>> getAllBuses() {
        return busService.getAllBuses();
    }

    // Search for buses using query parameters (GET request)
    @GetMapping("/search")
    public ResponseEntity<List<Bus>> searchBuses(
            @RequestParam String departureCity,
            @RequestParam String destinationCity,
            @RequestParam String startTime) {

        return busService.searchBuses(departureCity, destinationCity, startTime);
    }
}
