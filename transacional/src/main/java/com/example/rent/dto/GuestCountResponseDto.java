package com.example.rent.dto;

public record GuestCountResponseDto(
        String reservationId,
        long guestCount
) {
}

