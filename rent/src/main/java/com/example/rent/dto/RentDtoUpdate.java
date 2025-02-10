package com.example.rent.dto;


import java.io.Serializable;

public record RentDtoUpdate(
        Long id,
        Double iptu,
        Double water,
        Double energy,
        Double condominium,
        Double value,
        String description
) implements Serializable {}

