package com.unipampa.crud.validations.impl;

import com.unipampa.crud.dto.AccommodationDTO;
import com.unipampa.crud.enums.AccommodationType;
import com.unipampa.crud.exceptions.ValidateRegisterException;
import com.unipampa.crud.service.AccommodationService;
import com.unipampa.crud.validations.ValidationsRegisterAccommodation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ValidateAccommodationRegistered implements ValidationsRegisterAccommodation {

    @Autowired
    private AccommodationService service;

    @Override
    public void validate(AccommodationDTO entity) {
        if (entity.accommodationType().equals(AccommodationType.HOUSE)
                && service.existsByCodAddressAndNumber(entity.codAddress(), entity.streetNumber())) {
            log.error("HOUSE {} is already registered!", entity.description());
            throw new ValidateRegisterException("HOUSE is already registered!");
        }

    }
}
