package com.example.rent.dto;

import java.util.List;

public record ReservationDto(Long accommodationId,
                             List<Long> guestIds) {
}
