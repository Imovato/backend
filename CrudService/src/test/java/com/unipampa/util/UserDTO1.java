package com.unipampa.util;

import com.unipampa.crud.dto.ContactDTO;
import com.unipampa.crud.dto.UserDTO;

public class UserDTO1 {

    public static UserDTO createUserPostRequestBody() {
        return UserDTO.builder()
                .id(UserCreator.createValidUser().getId())
                .name(UserCreator.createUserToSaved().getName())
                .email(UserCreator.createUserToSaved().getEmail())
                .build();
    }
}
