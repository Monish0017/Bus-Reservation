package com.example.busreservation.service;

import com.example.busreservation.model.Payment;
import com.example.busreservation.model.Booking;
import com.example.busreservation.repository.PaymentRepository;
import com.example.busreservation.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public String processPayment(String bookingId, float amount, String paymentMethod) {
        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        if (booking != null) {
            Payment payment = new Payment();
            payment.setBooking(booking);
            payment.setAmount(amount);
            payment.setPaymentMethod(paymentMethod);
            payment.setStatus("completed");

            paymentRepository.save(payment);
            return "Payment processed successfully";
        } else {
            return "Invalid booking ID";
        }
    }
}
