package com.example.rent.service.impl;

import com.example.rent.entities.Accommodation;
import com.example.rent.enums.StatusAccommodation;
import com.example.rent.repository.AccommodationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccommodationServiceImplTest {

    @Mock
    private AccommodationRepository accommodationRepository;

    @InjectMocks
    private AccommodationServiceImpl accommodationService;

    private Accommodation mockAccommodation;

    @BeforeEach
    void setUp() {
        mockAccommodation = new Accommodation();
        mockAccommodation.setId(1L);
        mockAccommodation.setPrice(500.00);
    }


    @Test
    void testFindAccommodationByIdSuccess() {
        when(accommodationRepository.findById("abc")).thenReturn(Optional.of(mockAccommodation));

        Optional<Accommodation> result = accommodationService.findAccommodationById("abc");

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        assertEquals(500.00, result.get().getPrice());
        verify(accommodationRepository, times(1)).findById("abc");
    }

    @Test
    void testFindAccommodationByIdNotFound() {
        when(accommodationRepository.findById("xyz")).thenReturn(Optional.empty());

        Optional<Accommodation> result = accommodationService.findAccommodationById("xyz");

        assertFalse(result.isPresent());
        verify(accommodationRepository, times(1)).findById("xyz");
    }

    @Test
    void testChangeStatusForRentedSuccess() {
        mockAccommodation.setStatus(StatusAccommodation.RENTED);

        accommodationService.changeStatusForRented(mockAccommodation);

        verify(accommodationRepository, times(1)).save(mockAccommodation);
    }

}