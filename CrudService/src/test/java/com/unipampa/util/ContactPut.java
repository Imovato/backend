package com.unipampa.util;

import com.unipampa.crud.model.Contact;

public class ContactPut {
    public static Contact createContactPutRequestBody() {
        return Contact.builder()
                .id(ContactCreator.createValidUpdateContact().getId())
                .name(ContactCreator.createValidUpdateContact().getName())
                .build();
    }
}
