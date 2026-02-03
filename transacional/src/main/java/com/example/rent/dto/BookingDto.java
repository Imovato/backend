package com.example.rent.dto;

import java.util.List;

public record BookingDto(String accommodationId, List<String> guestIds, Integer rentalMonths) {
}
