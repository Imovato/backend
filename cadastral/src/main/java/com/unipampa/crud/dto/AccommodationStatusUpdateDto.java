package com.unipampa.crud.dto;

import com.unipampa.crud.enums.AccommodationStats;

public record AccommodationStatusUpdateDto(String id, AccommodationStats stats) {
}

