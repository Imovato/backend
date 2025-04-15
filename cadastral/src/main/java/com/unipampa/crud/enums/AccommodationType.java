package com.unipampa.crud.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum AccommodationType {

    APARTMENT,

    HOUSE;

//    @JsonCreator
//    public static AccommodationType fromString(String value) {
//        return AccommodationType.valueOf(value.toUpperCase());
//    }
}
