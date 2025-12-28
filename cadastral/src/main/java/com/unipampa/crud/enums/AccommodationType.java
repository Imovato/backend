package com.unipampa.crud.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;

public enum AccommodationType {

    @Schema(description = "Apartamento")
    APARTMENT,

    @Schema(description = "Casa")
    HOUSE;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static AccommodationType forValue(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        return AccommodationType.valueOf(value.toUpperCase().trim());
    }
}
