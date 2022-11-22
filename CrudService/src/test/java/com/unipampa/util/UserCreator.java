package com.unipampa.util;

import com.unipampa.crud.model.Contact;
import com.unipampa.crud.model.User;

public class UserCreator {

    public static User createUserToSaved() {
        return User.builder()
                .name("Mateus Balda")
                .email("mateusbalda89@gmail.com")
                .build();
    }
    public static User createValidUser() {
        return User.builder()
                .id(1L)
                .name("Mateus Balda")
                .email("mateusbalda89@gmail.com")
                .build();
    }
    public static User createValidUpdateUser() {
        return User.builder()
                .id(1L)
                .name("Mateus bla bla")
                .email("mateusbalda89@gmail.com")
                .build();
    }
}
