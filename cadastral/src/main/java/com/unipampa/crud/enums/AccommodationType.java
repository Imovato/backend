package com.unipampa.crud.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public enum AccommodationType {

    @Schema(description = "Apartamento")
    APARTMENT,

    @Schema(description = "Casa")
    HOUSE;

//    @JsonCreator
//    public static AccommodationType fromString(String value) {
//        return AccommodationType.valueOf(value.toUpperCase());
//    }
}
