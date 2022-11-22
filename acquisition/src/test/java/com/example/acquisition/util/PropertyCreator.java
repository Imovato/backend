package com.example.acquisition.util;

import com.example.acquisition.model.Property;

public class PropertyCreator {
    public static Property createPropertyToSaved() {
        return Property.builder()
                .id(133L)
                .price(133.0)
                .build();
    }

    public static Property createPropertyToTest1() {
        return Property.builder()
                .id(134L)
                .price(133.0)
                .build();
    }

    public static Property createPropertyToTest2() {
        return Property.builder()
                .id(135L)
                .price(133.0)
                .build();
    }
}
