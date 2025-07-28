package com.example.rent.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AccommodationDto(String id, Double price, int maxOccupancy) {
}

