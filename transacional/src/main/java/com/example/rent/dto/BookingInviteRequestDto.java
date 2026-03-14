package com.example.rent.dto;

import jakarta.validation.constraints.NotBlank;

public record BookingInviteRequestDto(@NotBlank String guestId) {
}

