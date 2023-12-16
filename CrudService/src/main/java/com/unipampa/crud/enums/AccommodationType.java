package com.unipampa.crud.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AccommodationType {

    @JsonProperty("apartment")
    APARTMENT,

    @JsonProperty("house")
    HOUSE
}
