package com.example.rent.service;

import com.example.rent.dto.BookingDto;
import com.example.rent.entities.Booking;


public interface BookingService {
    Booking createBooking(BookingDto request);

    Booking getBookingById(Long id) throws Exception;
    Booking cancelBooking(Long id) throws Exception;

    Booking updateBooking(Booking request) throws Exception;

    BookingDto payBooking(Long bookingId, String userId) throws Exception;
}
