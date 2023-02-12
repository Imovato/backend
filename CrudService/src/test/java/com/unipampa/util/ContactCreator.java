package com.unipampa.util;

import com.unipampa.crud.model.Contact;
import org.junit.jupiter.api.Test;

public class ContactCreator {
    public static Contact createContactToSaved() {
        return Contact.builder()
                .name("Mateus Balda")
                .email("mateusbalda89@gmail.com")
                .number("55999896728")
                .message("Bom dia/boa tarde/boa noite")
                .build();
    }
    public static Contact createValidContact() {
        return Contact.builder()
                .id(1L)
                .name("Mateus Balda")
                .email("mateusbalda89@gmail.com")
                .number("55999896728")
                .message("Bom dia/boa tarde/boa noite")
                .build();
    }
    public static Contact createValidUpdateContact() {
        return Contact.builder()
                .id(1L)
                .name("Mateus bla bla")
                .email("mateusbalda89@gmail.com")
                .number("55999896728")
                .message("Bom dia/boa tarde/boa noite")
                .build();
    }
}
