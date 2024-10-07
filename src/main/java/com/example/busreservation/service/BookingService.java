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
        Optional<User> optionalUser = userRepository.findByEmail(email);
        Optional<Bus> optionalBus = busRepository.findByBusNo(busNo);

        if (optionalUser.isPresent() && optionalBus.isPresent()) {
            User user = optionalUser.get();
            Bus bus = optionalBus.get();

            List<Integer> bookedSeats = new ArrayList<>();
            for (int requestedSeat : requestedSeats) {
                if (bus.getAvailableSeats().contains(requestedSeat)) {
                    bookedSeats.add(requestedSeat);
                } else {
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

            return "Booking confirmed for seats: " + bookedSeats;
        }

        return "User or Bus not found";
    }
}
