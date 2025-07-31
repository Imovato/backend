package com.example.rent.dto;

import com.example.rent.enums.UserStats;
import com.example.rent.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserDto(String id, String name, String email, UserType type, UserStats stats) {

}

