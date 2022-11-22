package com.unipampa.crud.implement.service.impl;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.unipampa.crud.dto.PropertyDTO;
import com.unipampa.crud.interfaces.service.StrategySaveProperty;
import com.unipampa.crud.model.Apartment;
import com.unipampa.crud.model.Property;

public class ApartmentSave implements StrategySaveProperty {
    @Override
    public Property save(PropertyDTO propertyDTO) {
        Apartment apartment = Apartment.builder()
                .area(propertyDTO.getArea())
                .name(propertyDTO.getName())
                .neighborhood(propertyDTO.getNeighborhood())
                .codAddress(propertyDTO.getCodAddress())
                .city(propertyDTO.getCity())
                .description(propertyDTO.getDescription())
                .adress(propertyDTO.getAdress())
                .state(propertyDTO.getState())
                .price(propertyDTO.getPrice())
                .number(propertyDTO.getNumber())
                .block(propertyDTO.getBlock())
                .rooms(propertyDTO.getRooms())
                .amount(propertyDTO.getAmount()).build();
        return apartment;
    }
}
