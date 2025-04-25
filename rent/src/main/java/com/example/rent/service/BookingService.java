package com.example.rent.service;

import com.example.rent.dto.BookingDto;
import com.example.rent.entities.Booking;

public interface BookingService {
    Booking createBooking(BookingDto request);
}
