package com.example.rent.dto;

import java.time.LocalDate;
import java.util.List;

public record BookingDto(String accommodationId, List<String> guestIds, LocalDate intialDate, LocalDate endDate) {
}
