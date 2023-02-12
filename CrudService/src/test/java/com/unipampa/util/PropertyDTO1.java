package com.unipampa.util;

import com.unipampa.crud.dto.PropertyDTO;

public class PropertyDTO1 {
    public static PropertyDTO createPropertyPostRequestBody() {
        return PropertyDTO.builder()
                .idProperty(PropertyCreator.createValidProperty().getId())
                .adress(PropertyCreator.createValidProperty().getAdress())
                .name(PropertyCreator.createValidProperty().getName())
                .city(PropertyCreator.createValidProperty().getCity())
                .build();
    }
}
