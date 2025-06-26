package com.unipampa.crud.validations.impl;

import com.unipampa.crud.dto.AccommodationDTO;
import com.unipampa.crud.dto.AccommodationRequestDTO;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ValidateAccommodationRegisteredTest {

    @Mock
    private AccommodationService service;

    @InjectMocks
    private ValidateAccommodationRegistered validateAccommodation;

    private AccommodationRequestDTO dtoHouse;

    @BeforeEach
    void setUp() {
        dtoHouse = new AccommodationRequestDTO(
                "Beautiful House",
                "Downtown",
                "ADDR123",
                "Springfield",
                "A spacious house with a garden",
                "123 Main St",
                "NY",
                new BigDecimal("250000.00"),
                5,
                5,
                AccommodationType.HOUSE,
                5,
                List.of("https://img.com/1.jpg", "https://img.com/2.jpg"),
                "12345"
        );
    }

    @Test
    void shouldValidateSuccessWhenHouseNotRegistered() {
        when(service.existsByCodAddressAndNumber(dtoHouse.codAddress(), dtoHouse.streetNumber())).thenReturn(false);

        assertDoesNotThrow(() -> validateAccommodation.validate(dtoHouse));

        verify(service).existsByCodAddressAndNumber(dtoHouse.codAddress(), dtoHouse.streetNumber());
    }

    @Test
    void testValidateFailureWhenHouseAlreadyRegistered() {
        when(service.existsByCodAddressAndNumber(dtoHouse.codAddress(), dtoHouse.streetNumber())).thenReturn(true);

        ValidateRegisterException exception = assertThrows(ValidateRegisterException.class, () -> {
            validateAccommodation.validate(dtoHouse);
        });

        assertEquals("HOUSE is already registered!", exception.getMessage());
        verify(service).existsByCodAddressAndNumber(dtoHouse.codAddress(), dtoHouse.streetNumber());
    }

}