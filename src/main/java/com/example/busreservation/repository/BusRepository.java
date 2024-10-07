package com.example.busreservation.repository;

import com.example.busreservation.model.Bus;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BusRepository extends MongoRepository<Bus, String> {
    List<Bus> findByDepartureCityAndDestinationCityAndDepartureTimeBetween(String departureCity, String destinationCity, String startTime, String endTime);
    
    Optional<Bus> findByBusNo(String busNo);  // Ensure Optional is imported
}
