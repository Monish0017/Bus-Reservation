package com.example.busreservation.service;

import com.example.busreservation.model.Bus;
import com.example.busreservation.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    // Fetch all buses
    public ResponseEntity<List<Bus>> getAllBuses() {
        List<Bus> buses = busRepository.findAll();
        if (buses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(buses, HttpStatus.OK);
    }

    // Search buses based on departure and destination cities, and a departure time
    // range
    public ResponseEntity<List<Bus>> searchBuses(String departureCity, String destinationCity, String startTime) {
        List<Bus> buses = busRepository.findByDepartureCityAndDestinationCityAndDepartureTime(departureCity,
                destinationCity, startTime);
        if (buses.isEmpty()) {
            return new ResponseEntity<>("No buses found for the given search criteria.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(buses, HttpStatus.OK);
    }

    // Get a specific bus by bus number
    public ResponseEntity<Bus> findBusByBusNo(String busNo) {
        Optional<Bus> optionalBus = busRepository.findByBusNo(busNo);
        if (optionalBus.isPresent()) {
            return new ResponseEntity<>(optionalBus.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Bus not found with the given bus number.", HttpStatus.NOT_FOUND);
    }
}
