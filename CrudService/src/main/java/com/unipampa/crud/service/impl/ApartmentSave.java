package com.unipampa.crud.service.impl;

import com.unipampa.crud.dto.PropertyDTO;
import com.unipampa.crud.service.StrategySaveProperty;
import com.unipampa.crud.model.Apartment;
import com.unipampa.crud.model.Hosting;

public class ApartmentSave implements StrategySaveProperty {
    @Override
    public Hosting save(PropertyDTO propertyDTO) {
//        Apartment apartment = Apartment.builder()
//                .area(propertyDTO.getArea())
//                .name(propertyDTO.getName())
//                .neighborhood(propertyDTO.getNeighborhood())
//                .codAddress(propertyDTO.getCodAddress())
//                .city(propertyDTO.getCity())
//                .description(propertyDTO.getDescription())
//                .adress(propertyDTO.getAdress())
//                .state(propertyDTO.getState())
//                .price(propertyDTO.getPrice())
//                .number(propertyDTO.getNumber())
//                .block(propertyDTO.getBlock())
//                .rooms(propertyDTO.getRooms())
//                .amount(propertyDTO.getAmount()).build();
        return null;
    }
}
