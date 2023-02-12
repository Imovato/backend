package com.example.acquisition.util;

import com.example.acquisition.model.User;

public class UserCreator {
    public static User createUserToSaved() {
        return User.builder()
                .name("João")
                .renda(12321.0)
                .cpf(String.valueOf(12312414))
                .id(13L)
                .build();
    }
}
