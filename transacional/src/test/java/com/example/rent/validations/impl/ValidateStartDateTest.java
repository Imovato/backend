package com.example.rent.validations.impl;

import com.example.rent.dto.RentDto;
import com.example.rent.entities.Accommodation;
import com.example.rent.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ValidateStartDateTest {

    private ValidateStartDate validate;

    @BeforeEach
    void setUp() {
        validate = new ValidateStartDate();

    }

    @Test
    void validate_Success() {
        RentDto validRentDto = new RentDto(
                Accommodation.builder().build(),
                User.builder().build(),
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 1, 15)
        );

        assertDoesNotThrow(() -> validate.validate(validRentDto));
    }

    @Test
    void validate_Fail_StartDateAfterEndDate() {
        RentDto invalidRentDto = new RentDto(
                Accommodation.builder().build(),
                User.builder().build(),
                LocalDate.of(2023, 2, 1),
                LocalDate.of(2023, 1, 31)
        );

        Exception exception = assertThrows(IllegalArgumentException.class, () -> validate.validate(invalidRentDto));

        assertEquals("A data de início deve ser anterior à data do fim.", exception.getMessage());
    }

}