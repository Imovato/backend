package com.example.rent.dto;

import java.time.LocalDate;
import java.util.List;

public record BookingDto(Long accommodationId, List<Long> guestIds, LocalDate intialDate, LocalDate endDate) {
}
