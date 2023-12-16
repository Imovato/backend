package com.unipampa.crud.service.impl;

import com.unipampa.crud.enums.AccommodationType;
import com.unipampa.crud.model.Accommodation;
import com.unipampa.crud.repository.AccommodationRepository;
import com.unipampa.crud.sender.PropertySender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccommodationServiceImplTest {

    @InjectMocks
    private AccommodationServiceImpl service;

    @Mock
    private AccommodationRepository propertyRepository;

    @Mock
    private PropertySender propertySender;


    @Captor
    private ArgumentCaptor<Accommodation> argumentCaptor;

    @Mock
    private Accommodation accommodation;

    @Test
    void shouldSaveAccommodationTypeHouse() {
        Accommodation accommodation = Accommodation.builder()
                .id("123")
                .title("Casa na praia")
                .description("Casa na praia a 300 metros do mar")
                .neighborhood("Praia do Gonzaga")
                .codAddress("12912391")
                .city("Santos")
                .adress("avenia beira-mar 1200")
                .state("SP")
                .price(500.00)
                .number(12L)
                .imageQuantity(10)
                .accommodationType(AccommodationType.HOUSE)
                .build();

        service.save(accommodation);

        then(propertyRepository).should().save(argumentCaptor.capture());
        Accommodation accommodationResult = argumentCaptor.getValue();
        assertEquals(accommodation.getAccommodationType(), accommodationResult.getAccommodationType());
    }

    @Test
    void shouldGetListOfAllAccommodations() {
        List<Accommodation> accommodations = service.findAll();
        then(propertyRepository).should().findAll();
        assertNotNull(accommodations);
    }

    @Test
    void shouldDeleteAccommodationById() {
        service.delete("123");
        then(propertyRepository).should().deleteById("123");
    }

    @Test
    void shouldFindAccommodationById() {
        String id = "123";
        when(service.findById(id)).thenReturn(Optional.of(accommodation));
        Optional<Accommodation> accommodationReturn = service.findById(id);
        then(propertyRepository).should().findById(id);
        assertNotNull(accommodationReturn);
    }
}