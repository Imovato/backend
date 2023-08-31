package com.unipampa.crud.service.impl;

import com.unipampa.crud.dto.PropertyDTO;
import com.unipampa.crud.service.StrategySaveProperty;
import com.unipampa.crud.model.Ground;
import com.unipampa.crud.model.Hosting;

public class GroundSave implements StrategySaveProperty {

    @Override
    public Hosting save(PropertyDTO propertyDTO) {
        Ground ground = (Ground) Ground.builder()
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
