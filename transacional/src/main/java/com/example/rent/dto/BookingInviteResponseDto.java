package com.example.rent.dto;

import com.example.rent.enums.InviteStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BookingInviteResponseDto(
        String inviteId,
        String bookingId,
        String guestId,
        InviteStatus status,
        BigDecimal shareAmount,
        LocalDateTime deadline
) {
}

