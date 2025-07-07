package com.example.rent.validations.impl;

import com.example.rent.dto.RentDto;
import com.example.rent.entities.Accommodation;
import com.example.rent.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ValidateMinimumDaysForRentTest {

    private ValidateMinimumDaysForRent validate;

    private static final long MIN_DAYS = 30;

    @BeforeEach
    void setUp() {
         validate = new ValidateMinimumDaysForRent();

    }

    @Test
    void validate_Success() {
        RentDto validRentDto = new RentDto(
                Accommodation.builder().build(),
                User.builder().build(),
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 2, 1)
        );

        assertDoesNotThrow(() -> validate.validate(validRentDto));
    }

    @Test
    void validate_Fail_PeriodTooShort() {
        RentDto invalidRentDto = new RentDto(
                Accommodation.builder().build(),
                User.builder().build(),
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 1, 15)
        );

        Exception exception = assertThrows(IllegalArgumentException.class, () -> validate.validate(invalidRentDto));

        assertEquals("O período contratado não pode ser menor do que 30 dias.", exception.getMessage());
    }
}