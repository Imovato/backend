package com.example.rent.validations.impl;

import com.example.rent.dto.RentDto;
import com.example.rent.validations.DateValidations;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;

@Component
public class ValidateMinimumDaysForRent implements DateValidations {

    public static final int MIN_DAYS = 30;

    @Override
    public void validate(RentDto dto) {
        long daysBetween = ChronoUnit.DAYS.between(dto.getStartDateRent(), dto.getEndDateRent());
        if(daysBetween < MIN_DAYS) {
            System.out.println("o período contratado não pode ser menor do que 30 dias.");
        }
    }
}
