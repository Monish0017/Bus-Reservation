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

    @GetMapping("/search")
    public List<Bus> searchBuses(@RequestParam String departureCity, @RequestParam String destinationCity) {
        return busService.searchBuses(departureCity, destinationCity);
    }
}
