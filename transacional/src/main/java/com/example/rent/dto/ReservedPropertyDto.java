package com.example.rent.dto;

import com.example.rent.enums.StatusReservation;

import java.time.LocalDate;

public record ReservedPropertyDto(
        Long bookingId,
        String accommodationId,
        StatusReservation statusReservation,
        LocalDate initialDate,
        LocalDate endDate,
        AccommodationDetailsDto accommodationDetails
) {
}
