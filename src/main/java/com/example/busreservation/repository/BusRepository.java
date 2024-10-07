package com.example.busreservation.repository;

import com.example.busreservation.model.Bus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BusRepository extends MongoRepository<Bus, String> {
    List<Bus> findByDepartureCityAndDestinationCityAndDepartureTime(String departureCity, String destinationCity, String departureTime);
    
    Optional<Bus> findByBusNo(String busNo);
}
