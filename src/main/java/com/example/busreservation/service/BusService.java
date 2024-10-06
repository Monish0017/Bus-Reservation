package com.example.busreservation.service;

import com.example.busreservation.model.Bus;
import com.example.busreservation.model.Booking;
import com.example.busreservation.model.User;
import com.example.busreservation.repository.BusRepository;
import com.example.busreservation.repository.BookingRepository;
import com.example.busreservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Bus> searchBuses(String departureCity, String destinationCity) {
        return busRepository.findByDepartureCityAndDestinationCity(departureCity, destinationCity);
    }
    
    public Bus getBusById(String busId) {
        return busRepository.findById(busId).orElse(null);
    }

    public String bookSeat(String busNo, String userEmail, int seatNumber) {
        Optional<Bus> optionalBus = busRepository.findByBusNo(busNo); // Get Optional<Bus>
        Optional<User> optionalUser = userRepository.findByEmail(userEmail); // Get Optional<User>

        if (optionalBus.isPresent() && optionalUser.isPresent()) {
            Bus bus = optionalBus.get(); // Extract Bus
            User user = optionalUser.get(); // Extract User
            
            if (bus.getAvailableSeats().contains(seatNumber)) {
                Booking booking = new Booking();
                booking.setBus(bus);
                booking.setUser(user);
                booking.setBookingDate(new Date());
                booking.setStatus("Pending");
                booking.setBookedSeats(Collections.singletonList(seatNumber));

                bus.getAvailableSeats().remove((Integer) seatNumber);
                busRepository.save(bus);
                bookingRepository.save(booking);
                return "Seat booked successfully!";
            }
        }
        return "Seat not available or user not found!";
    }
}
