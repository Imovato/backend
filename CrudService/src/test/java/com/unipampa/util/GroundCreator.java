package com.unipampa.util;

import com.unipampa.crud.model.Apartment;
import com.unipampa.crud.model.Ground;

public class GroundCreator {
    public static Ground createGroundToSaved() {
        return Ground.builder()
                .name("Bla bla bla")
                .dtype("Ground")
                .adress("Avenida São José")
                .area(35)
                .city("Alegrete")
                .number(514L)
                .price(100000)
                .description("Area grande, bom para familia")
                .build();
    }
    public static Ground createValidGround() {
        return Ground.builder()
                .id(1L)
                .name("Bla bla bla")
                .dtype("Ground")
                .adress("Avenida São José")
                .area(35)
                .city("Alegrete")
                .number(514L)
                .price(100000)
                .description("Area grande, bom para familia")
                .build();
    }
    public static Ground createValidUpdateGround() {
        return Ground.builder()
                .id(1L)
                .name("Bla bla bla")
                .dtype("Ground")
                .adress("Avenida São José")
                .area(35)
                .city("Alegrete")
                .number(514L)
                .price(100000)
                .description("Area grande, bom para familia")
                .build();
    }
}
