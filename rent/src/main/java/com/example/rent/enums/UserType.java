package com.example.rent.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UserType {

    HOST,

    GUEST,

    ADMINISTRATOR;

    @JsonCreator
    public static UserType fromString(String value) {
        return UserType.valueOf(value.toUpperCase());
    }
}
