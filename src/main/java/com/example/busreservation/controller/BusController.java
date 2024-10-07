package com.example.busreservation.controller;

import com.example.busreservation.model.Bus;
import com.example.busreservation.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buses")
public class BusController {

    @Autowired
    private BusService busService;

    // Search for buses with bus data (POST request)
    @PostMapping("/search")
    public List<Bus> searchBuses(@RequestBody Bus searchRequest) {
        // Use the date strings directly without parsing
        return busService.searchBuses(
                searchRequest.getDepartureCity(),
                searchRequest.getDestinationCity(),
                searchRequest.getDepartureTime(),
                searchRequest.getArrivalTime()
        );
    }
}
