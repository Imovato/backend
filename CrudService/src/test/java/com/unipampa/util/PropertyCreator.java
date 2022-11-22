package com.unipampa.util;

import com.unipampa.crud.model.Property;

public class PropertyCreator {
    public static Property createPropertyToSaved() {
        return Property.builder()
                .name("Bla bla bla")
                .dtype("Property")
                .adress("Avenida São José")
                .area(35)
                .city("Alegrete")
                .number(514L)
                .price(100000)
                .description("Area grande, bom para familia")
                .build();
    }
    public static Property createValidProperty() {
        return Property.builder()
                .id(1L)
                .name("Bla bla bla")
                .dtype("Property")
                .adress("Avenida São José")
                .area(35)
                .city("Alegrete")
                .number(514L)
                .price(100000)
                .description("Area grande, bom para familia")
                .build();
    }
    public static Property createValidUpdateProperty() {
        return Property.builder()
                .id(1L)
                .name("Bla bla bla")
                .dtype("Property")
                .adress("Avenida São José")
                .area(35)
                .city("Alegrete")
                .number(514L)
                .price(100000)
                .description("Area grande, bom para familia")
                .build();
    }
}
