package com.example.rent.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum StatusAccommodation {
    RENTED,
    AVAILABLE;

    @JsonCreator
    public static StatusAccommodation fromString(String value) {
        return StatusAccommodation.valueOf(value.toUpperCase());
    }
}
