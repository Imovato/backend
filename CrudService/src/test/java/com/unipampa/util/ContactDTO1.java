package com.unipampa.util;


public class ContactDTO1 {
    public static ContactDTO createContactPostRequestBody() {
        return ContactDTO.builder()
                .id(ContactCreator.createValidContact().getId())
                .name(ContactCreator.createContactToSaved().getName())
                .email(ContactCreator.createContactToSaved().getEmail())
                .build();
    }
}
