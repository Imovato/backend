package com.unipampa.crud.validations.impl;

import com.unipampa.crud.dto.AccommodationDTO;
import com.unipampa.crud.enums.AccommodationType;
import com.unipampa.crud.exceptions.ValidateRegisterException;
import com.unipampa.crud.service.AccommodationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ValidateAccommodationRegisteredTest {

    @Mock
    private AccommodationService service;

    @InjectMocks
    private ValidateAccommodationRegistered validateAccommodation;

    private AccommodationDTO dtoHouse;

    @BeforeEach
    void setUp() {
        dtoHouse = new AccommodationDTO(
                "Beautiful House",
                "Downtown",
                "ADDR123",
                "Springfield",
                "A spacious house with a garden",
                "123 Main St",
                "NY",
                new BigDecimal("250000.00"),
                101,
                5,
                AccommodationType.HOUSE
        );
    }

    @Test
    void shouldValidateSuccessWhenHouseNotRegistered() {
        when(service.existsByCodAddressAndNumber(dtoHouse.codAddress(), dtoHouse.number())).thenReturn(false);

        assertDoesNotThrow(() -> validateAccommodation.validate(dtoHouse));

        verify(service).existsByCodAddressAndNumber(dtoHouse.codAddress(), dtoHouse.number());
    }

    @Test
    void testValidateFailureWhenHouseAlreadyRegistered() {
        when(service.existsByCodAddressAndNumber(dtoHouse.codAddress(), dtoHouse.number())).thenReturn(true);

        ValidateRegisterException exception = assertThrows(ValidateRegisterException.class, () -> {
            validateAccommodation.validate(dtoHouse);
        });

        assertEquals("HOUSE is already registered!", exception.getMessage());
        verify(service).existsByCodAddressAndNumber(dtoHouse.codAddress(), dtoHouse.number());
    }

}