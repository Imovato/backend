package com.example.rent.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UserType {

    ROLE_HOST,

    ROLE_GUEST,

    ROLE_ADMINISTRATOR;

    @JsonCreator
    public static UserType fromString(String value) {
        return UserType.valueOf(value.toUpperCase());
    }
}
