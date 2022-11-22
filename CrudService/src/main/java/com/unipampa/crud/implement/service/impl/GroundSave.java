package com.unipampa.crud.implement.service.impl;

import com.unipampa.crud.dto.PropertyDTO;
import com.unipampa.crud.interfaces.service.StrategySaveProperty;
import com.unipampa.crud.model.Ground;
import com.unipampa.crud.model.Property;

public class GroundSave implements StrategySaveProperty {

    @Override
    public Property save(PropertyDTO propertyDTO) {
        Ground ground = Ground.builder()
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
                .amount(propertyDTO.getAmount()).build();
        return ground;
    }
}
