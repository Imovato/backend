package com.unipampa.crud.service.impl;

import com.unipampa.crud.dto.PropertyDTO;
import com.unipampa.crud.service.StrategySaveProperty;
import com.unipampa.crud.model.House;
import com.unipampa.crud.model.Property;

public class HouseSave implements StrategySaveProperty {
    @Override
    public Property save(PropertyDTO propertyDTO) {
        House house = House.builder()
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
                .rooms(propertyDTO.getRooms())
                .amount(propertyDTO.getAmount()).build();
        return house;
    }
}
