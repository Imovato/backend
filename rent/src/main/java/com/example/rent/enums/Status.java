package com.example.rent.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Status {
    RENTED,
    AVAILABLE;

    @JsonCreator
    public static Status fromString(String value) {
        return Status.valueOf(value.toUpperCase());
    }
}
