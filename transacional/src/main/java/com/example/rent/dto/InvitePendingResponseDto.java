package com.example.rent.dto;

import com.example.rent.enums.InviteStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record InvitePendingResponseDto(
        String inviteId,
        String bookingId,
        String propertyTitle,
        String propertyAddress,
        String hostName,
        BigDecimal shareAmount,
        BigDecimal totalAmount,
        Integer totalParticipants,
        LocalDateTime checkInDate,
        LocalDateTime checkOutDate,
        LocalDateTime deadline,
        InviteStatus status
) {
}

