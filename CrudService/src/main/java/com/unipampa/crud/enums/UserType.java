package com.unipampa.crud.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum UserType {

    @JsonProperty("host")
    HOST,

    @JsonProperty("guest")
    GUEST
}
