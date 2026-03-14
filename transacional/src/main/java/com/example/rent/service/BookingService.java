package com.example.rent.service;

import com.example.rent.dto.BookingDto;
import com.example.rent.dto.ReservedPropertyDto;
import com.example.rent.entities.Booking;
import com.example.rent.dto.BookingInviteRequestDto;
import com.example.rent.dto.BookingInviteResponseDto;

import java.util.List;


public interface BookingService {
    Booking createBooking(BookingDto request);

    Booking getBookingById(Long id) throws Exception;
    Booking cancelBooking(Long id) throws Exception;

    Booking updateBooking(Booking request) throws Exception;

    BookingDto payBooking(Long bookingId, String userId) throws Exception;

    List<ReservedPropertyDto> getReservedPropertiesByUser(String userId) throws Exception;

    BookingInviteResponseDto createBookingInvite(Long bookingId, BookingInviteRequestDto request) throws Exception;
}
