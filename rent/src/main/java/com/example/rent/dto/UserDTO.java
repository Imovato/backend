package com.example.rent.dto;

import com.example.rent.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    private String name;

    private String email;

    private UserType type;
}
