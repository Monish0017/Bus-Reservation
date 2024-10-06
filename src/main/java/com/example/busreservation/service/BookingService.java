package com.example.busreservation.service;

import com.example.busreservation.model.Booking;
import com.example.busreservation.model.Bus;
import com.example.busreservation.model.User;
import com.example.busreservation.repository.BookingRepository;
import com.example.busreservation.repository.BusRepository;
import com.example.busreservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private UserRepository userRepository;

    public String bookTicket(String email, String busNo, int[] requestedSeats) {
        System.out.println("Booking process started for email: " + email + ", busNo: " + busNo + ", requestedSeats: " + requestedSeats);

        Optional<User> optionalUser = userRepository.findByEmail(email); // Get Optional<User>
        Optional<Bus> optionalBus = busRepository.findByBusNo(busNo); // Get Optional<Bus>

        // Debugging User
        if (optionalUser.isPresent()) {
            System.out.println("User found: " + optionalUser.get());
        } else {
            System.out.println("User not found with email: " + email);
        }

        // Debugging Bus
        if (optionalBus.isPresent()) {
            System.out.println("Bus found: " + optionalBus.get());
        } else {
            System.out.println("Bus not found with bus number: " + busNo);
        }

        if (optionalUser.isPresent() && optionalBus.isPresent()) {
            User user = optionalUser.get(); // Extract User
            Bus bus = optionalBus.get(); // Extract Bus
            
            // Check if all requested seats are available
            List<Integer> bookedSeats = new ArrayList<>();
            for (int requestedSeat : requestedSeats) {
                if (bus.getAvailableSeats().contains(requestedSeat)) {
                    bookedSeats.add(requestedSeat);
                } else {
                    System.out.println("Requested seat " + requestedSeat + " is not available.");
                    return "Requested seat " + requestedSeat + " is not available.";
                }
            }

            // Proceed with booking
            Booking booking = new Booking();
            booking.setUser(user);
            booking.setBus(bus);
            booking.setBookingDate(new Date());
            booking.setStatus("confirmed");

            // Remove booked seats from available seats
            for (int seat : bookedSeats) {
                bus.getAvailableSeats().remove((Integer) seat);
            }

            booking.setBookedSeats(bookedSeats);
            bookingRepository.save(booking);
            busRepository.save(bus);

            // Debugging successful booking
            System.out.println("Booking confirmed. Booked seats: " + bookedSeats);
            return "Booking confirmed for seats: " + bookedSeats;
        }

        // If either user or bus is not found
        System.out.println("Booking failed. User or Bus not found");
        return "User or Bus not found";
    }
}
