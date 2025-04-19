package com.unipampa.crud.service.impl;

import com.unipampa.crud.entities.Accommodation;
import com.unipampa.crud.repository.AccommodationRepository;
import com.unipampa.crud.sender.AccommodationSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccommodationServiceImplTest {

    @Mock
    private AccommodationRepository propertyRepository;

    @Mock
    private AccommodationSender accommodationSender;

    @InjectMocks
    private AccommodationServiceImpl accommodationService;

    private Accommodation accommodation;

    @BeforeEach
    void setUp() {
        accommodation =  Accommodation.builder()
                .id("1")
                .addressNumber(101)
                .postalCode("ADDR123")
                .build();
    }

    @Test
    void testSaveSuccess() {
        Accommodation savedAccommodation = Accommodation.builder().id("1").build();
        when(propertyRepository.save(accommodation)).thenReturn(savedAccommodation);

        accommodationService.save(accommodation);

        verify(propertyRepository).save(accommodation);
        verify(accommodationSender).sendMessage(savedAccommodation);
    }

    @Test
    void testSaveFailure() {
        when(propertyRepository.save(accommodation)).thenThrow(new RuntimeException("Database error"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            accommodationService.save(accommodation);
        });

        assertEquals("Database error", exception.getMessage());
        verify(accommodationSender, never()).sendMessage(any());
    }

    @Test
    void testFindAllNonEmpty() {
        Accommodation accommodation1 = Accommodation.builder().id("1").build();
        Accommodation accommodation2 = Accommodation.builder().id("2").build();
        List<Accommodation> list = Arrays.asList(accommodation1, accommodation2);
        when(propertyRepository.findAll()).thenReturn(list);

        List<Accommodation> result = accommodationService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("1", result.get(0).getId());
        verify(propertyRepository).findAll();
    }

    @Test
    void testFindAllEmpty() {
        when(propertyRepository.findAll()).thenReturn(Collections.emptyList());

        List<Accommodation> result = accommodationService.findAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(propertyRepository).findAll();
    }

    @Test
    void testDeleteSuccess() {
        doNothing().when(propertyRepository).deleteById(accommodation.getId());

        accommodationService.delete(accommodation.getId());

        verify(propertyRepository, times(1)).deleteById(accommodation.getId());
    }

    @Test
    void testDeleteWhenExceptionThrown() {
        doThrow(new RuntimeException("Deletion failed")).when(propertyRepository).deleteById(accommodation.getId());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            accommodationService.delete(accommodation.getId());
        });

        assertEquals("Deletion failed", exception.getMessage());
        verify(propertyRepository, times(1)).deleteById(accommodation.getId());
    }

    @Test
    void testExistsByCodAddressAndNumberReturnsTrue() {
        String codeAddress = accommodation.getPostalCode();
        int number = accommodation.getAddressNumber();
        when(propertyRepository.existsByZipCodeAndStreetNumber(codeAddress, number)).thenReturn(true);

        boolean result = accommodationService.existsByCodAddressAndNumber(codeAddress, number);

        assertTrue(result);
        verify(propertyRepository, times(1)).existsByZipCodeAndStreetNumber(codeAddress, number);
    }

    @Test
    void testExistsByCodAddressAndNumberReturnsFalse() {
        String codeAddress = accommodation.getPostalCode();
        int number = accommodation.getAddressNumber();
        when(propertyRepository.existsByZipCodeAndStreetNumber(codeAddress, number)).thenReturn(false);

        boolean result = accommodationService.existsByCodAddressAndNumber(codeAddress, number);

        assertFalse(result);
        verify(propertyRepository, times(1)).existsByZipCodeAndStreetNumber(codeAddress, number);
    }

    @Test
    void testFindByIdFound() {
        String id = accommodation.getId();
        when(propertyRepository.findById(id)).thenReturn(Optional.of(accommodation));

        Optional<Accommodation> result = accommodationService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
        verify(propertyRepository, times(1)).findById(id);
    }

    @Test
    void testFindByIdNotFound() {
        String id = "456";
        when(propertyRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Accommodation> result = accommodationService.findById(id);

        assertFalse(result.isPresent());
        verify(propertyRepository, times(1)).findById(id);
    }
}