package com.example.rent.util;

import com.example.rent.enums.Status;
import com.example.rent.entities.Property;

public class PropertyCreator {
    public static Property createProperty() {
        return Property.builder()
                .id(1L)
                .salesman("Júlia")
                .status(Status.AVAILABLE)
                .build();
    }
}
