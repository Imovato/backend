package com.example.rent.mapper;

import com.example.rent.dto.BookingDto;
import com.example.rent.entities.Accommodation;
import com.example.rent.entities.Booking;
import com.example.rent.entities.GuestBooking;
import com.example.rent.enums.StatusReservation;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class BookingMapper {

//    public static Booking toEntity(BookingDto dto, Accommodation accommodation, List<GuestBooking> guestBookings) {
//        Booking booking = new Booking();
//        booking.setAccommodation(accommodation);
//        booking.setGuests(guestBookings);
//        booking.setInitialDate(dto.intialDate());
//        booking.setEndDate(dto.endDate());
//        booking.setStatusReservation(StatusReservation.WAITING_PAYMENT);
//        booking.setCreationDate(LocalDateTime.now());
//        booking.setExpiresDate(null);
//        return booking;
//    }

    public static BookingDto toDto(Booking booking) {
        List<String> guestIds = booking.getGuests().stream()
                .map(guestBooking -> guestBooking.getGuest().getId())
                .toList();

        int rentalMonths = (int) ChronoUnit.MONTHS.between(
                booking.getInitialDate(),
                booking.getEndDate()
        );

        return new BookingDto(
                booking.getAccommodation().getId(),
                guestIds,
                rentalMonths
        );
    }
}
