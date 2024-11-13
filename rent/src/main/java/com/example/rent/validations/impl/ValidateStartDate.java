package com.example.rent.validations.impl;

import com.example.rent.dto.RentDto;
import com.example.rent.validations.DateValidations;
import org.springframework.stereotype.Component;

@Component
public class ValidateStartDate implements DateValidations {
    @Override
    public void validate(RentDto dto) {
        if(dto.getStartDateRent().isAfter(dto.getEndDateRent())){
            System.out.println("a data de início deve ser anterior a data do fim.");
        }
    }
}
