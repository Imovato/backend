package com.unipampa.util;

import com.unipampa.crud.model.Accommodation;

public class PropertyCreator {
    public static Accommodation createPropertyToSaved() {
        return Accommodation.builder()
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
    public static Accommodation createValidProperty() {
        return Accommodation.builder()
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
    public static Accommodation createValidUpdateProperty() {
        return Accommodation.builder()
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
