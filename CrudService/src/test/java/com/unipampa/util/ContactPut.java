package com.unipampa.util;

public class ContactPut {
    public static Contact createContactPutRequestBody() {
        return Contact.builder()
                .id(ContactCreator.createValidUpdateContact().getId())
                .name(ContactCreator.createValidUpdateContact().getName())
                .build();
    }
}
