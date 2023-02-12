package com.unipampa.util;

import com.unipampa.crud.model.Apartment;

public class ApartmentCreator {
    public static Apartment createApartmentToSaved() {
        return Apartment.builder()
                .name("Bla bla bla")
                .dtype("Apartment")
                .adress("Avenida São José")
                .area(35)
                .city("Alegrete")
                .number(514L)
                .price(100000)
                .description("Area grande, bom para familia")
                .build();
    }
    public static Apartment createValidApartment() {
        return Apartment.builder()
                .id(1L)
                .name("Bla bla bla")
                .dtype("Apartment")
                .adress("Avenida São José")
                .area(35)
                .city("Alegrete")
                .number(514L)
                .price(100000)
                .description("Area grande, bom para familia")
                .build();
    }
    public static Apartment createValidUpdateApartment() {
        return Apartment.builder()
                .id(1L)
                .name("Bla bla bla")
                .dtype("Apartment")
                .adress("Avenida São José")
                .area(35)
                .city("Alegrete")
                .number(514L)
                .price(100000)
                .description("Area grande, bom para familia")
                .build();
    }
}
