package com.unipampa.util;


import com.unipampa.crud.dto.ContactDTO;

public class ContactDTO1 {
    public static ContactDTO createContactPostRequestBody() {
        return ContactDTO.builder()
                .id(ContactCreator.createValidContact().getId())
                .name(ContactCreator.createContactToSaved().getName())
                .email(ContactCreator.createContactToSaved().getEmail())
                .build();
    }
}
