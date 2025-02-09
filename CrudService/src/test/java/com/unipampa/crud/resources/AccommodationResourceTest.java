package com.unipampa.crud.resources;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unipampa.crud.dto.AccommodationDTO;
import com.unipampa.crud.entities.Accommodation;
import com.unipampa.crud.service.AccommodationService;
import com.unipampa.crud.validations.ValidationsRegisterAccommodation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Validation;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class AccommodationResourceTest {

    @Mock
    private AccommodationService accommodationService;

    @Mock
    private ObjectMapper mapper;

    @Mock
    private Validation validation;

    @InjectMocks
    private AccommodationResource accommodationResource;

    private AccommodationDTO accommodationDTO;
    private Accommodation accommodation;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testSaveAccommodation() {

    }
}