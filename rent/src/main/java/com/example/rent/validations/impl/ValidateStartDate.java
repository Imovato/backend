package com.example.rent.validations.impl;

import com.example.rent.dto.RentDto;
import com.example.rent.validations.DateValidations;
import org.springframework.stereotype.Component;

@Component
public class ValidateStartDate implements DateValidations {
    @Override
    public void validate(RentDto dto) {
        if(dto.startDateRent().isAfter(dto.endDateRent())){
            throw new IllegalArgumentException("A data de início deve ser anterior à data do fim.");
        }
    }
}
