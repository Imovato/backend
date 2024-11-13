package com.example.rent.util;

import com.example.rent.enums.Status;
import com.example.rent.entities.Accommodation;

public class PropertyCreator {
    public static Accommodation createProperty() {
        return Accommodation.builder()
                .id(1L)
                .salesman("Júlia")
                .status(Status.AVAILABLE)
                .build();
    }
}
