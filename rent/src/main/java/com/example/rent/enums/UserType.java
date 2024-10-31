package com.example.rent.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum UserType {

    @JsonProperty("host")
    HOST,

    @JsonProperty("guest")
    GUEST,

    @JsonProperty("administrator")
    ADMINITSTRATOR
}
