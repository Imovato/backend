package com.unipampa.util;

import com.unipampa.crud.model.Hosting;

public class PropertyCreator {
    public static Hosting createPropertyToSaved() {
        return Hosting.builder()
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
    public static Hosting createValidProperty() {
        return Hosting.builder()
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
    public static Hosting createValidUpdateProperty() {
        return Hosting.builder()
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
