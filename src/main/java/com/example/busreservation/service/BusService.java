package com.example.busreservation.service;

import com.example.busreservation.model.Bus;
import com.example.busreservation.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    // Search buses based on departure and destination cities, and a departure time range
    public List<Bus> searchBuses(String departureCity, String destinationCity, String startTime, String endTime) {
        return busRepository.findByDepartureCityAndDestinationCityAndDepartureTimeBetween(departureCity, destinationCity, startTime, endTime);
    }
    
    // Get a specific bus by bus number
    public Bus findBusByBusNo(String busNo) {
        return busRepository.findByBusNo(busNo).orElse(null);
    }
}
