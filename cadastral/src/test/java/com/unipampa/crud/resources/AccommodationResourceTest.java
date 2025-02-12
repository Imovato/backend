package com.unipampa.crud.resources;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unipampa.crud.dto.AccommodationDTO;
import com.unipampa.crud.entities.Accommodation;
import com.unipampa.crud.enums.AccommodationType;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.validation.Validation;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AccommodationResourceTest {

    @Mock
    private AccommodationService accommodationService;

    @Mock
    private ObjectMapper mapper;

    @Mock
    private List<ValidationsRegisterAccommodation> validations;

    @InjectMocks
    private AccommodationResource accommodationResource;

    private AccommodationDTO accommodationDTO;
    private Accommodation accommodation;


    @BeforeEach
    void setUp() {
        accommodationDTO = new AccommodationDTO(
                "Cozy Apartment",
                "Downtown",
                "123456",
                "New York",
                "A beautiful and cozy apartment in the city center.",
                "123 Main Street",
                "NY",
                BigDecimal.valueOf(1500.00),
                101,
                5,
                AccommodationType.APARTMENT
        );
        accommodation = Accommodation.builder().build();

    }

    @Test
    void shouldSaveAccommodationSuccessfully() {
        when(mapper.convertValue(accommodationDTO, Accommodation.class)).thenReturn(accommodation);
        doNothing().when(accommodationService).save(accommodation);

        ResponseEntity<Accommodation> response = accommodationResource.save(accommodationDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(accommodation, response.getBody());
        verify(validations, times(1)).forEach(any());
        verify(accommodationService, times(1)).save(accommodation);
    }

    @Test
    void shouldSaveFailureValidationException() {
        AccommodationDTO invalidDTO = new AccommodationDTO(null, null, null,
                null, null, null, null, null,
                0, 0, null);

        doThrow(new RuntimeException("Erro de validação"))
                .when(validations).forEach(any());

        Exception exception = assertThrows(RuntimeException.class, () -> accommodationResource.save(invalidDTO));
        assertEquals("Erro de validação", exception.getMessage());

        verify(accommodationService, never()).save(any(Accommodation.class));
    }


    @Test
    void shouldSaveFailureServiceException() {
        when(mapper.convertValue(accommodationDTO, Accommodation.class)).thenReturn(accommodation);

        doThrow(new RuntimeException("Erro ao salvar no banco"))
                .when(accommodationService).save(accommodation);

        Exception exception = assertThrows(RuntimeException.class, () -> accommodationResource.save(accommodationDTO));
        assertEquals("Erro ao salvar no banco", exception.getMessage());
    }

    @Test
    void shouldFindAllSuccess() {
        var accommodation2 = Accommodation.builder().build();
        List<Accommodation> mockAccommodations = Arrays.asList(accommodation, accommodation2);
        when(accommodationService.findAll()).thenReturn(mockAccommodations);

        ResponseEntity<List<Accommodation>> response = accommodationResource.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void shouldFindAllFailure() {
        when(accommodationService.findAll()).thenThrow(new RuntimeException("Erro interno"));

        Exception exception = assertThrows(RuntimeException.class, () -> accommodationResource.findAll());
        assertEquals("Erro interno", exception.getMessage());
    }

    @Test
    void shouldDeleteAccommodationSuccess() {
        var mockAccommodation = Accommodation.builder().build();
        when(accommodationService.findById(accommodation.getId())).thenReturn(Optional.of(mockAccommodation));

        ResponseEntity<Object> response = accommodationResource.deleteAccommodation(accommodation.getId());

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(accommodationService, times(1)).delete(accommodation.getId());
    }

    @Test
    void shouldDeleteAccommodationNotFound() {

        when(accommodationService.findById(accommodation.getId())).thenReturn(Optional.empty());

        ResponseEntity<Object> response = accommodationResource.deleteAccommodation(accommodation.getId());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Acomodação não encontrada para esse id, portanto não pode ser deletada!", response.getBody());
        verify(accommodationService, never()).delete(accommodation.getId());
    }

    @Test
    void shouldUpdateAccommodationSuccess() {
        when(accommodationService.findById(accommodation.getId())).thenReturn(Optional.of(accommodation));
        doNothing().when(validations).forEach(any());

        ResponseEntity<Object> response = accommodationResource.updateAccommodation(accommodation.getId(), accommodationDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(accommodationService).save(any());
    }


    @Test
    void shouldUpdateAccommodationNotFound() {
        when(accommodationService.findById(accommodation.getId())).thenReturn(Optional.empty());

        ResponseEntity<Object> response = accommodationResource.updateAccommodation(accommodation.getId(), accommodationDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(accommodationService, never()).save(any());
    }

    @Test
    void shouldUpdateAccommodationInvalidData() {
        when(accommodationService.findById(accommodation.getId())).thenReturn(Optional.of(accommodation));
        doThrow(new RuntimeException("Erro")).when(validations).forEach(any());

        ResponseEntity<Object> response = accommodationResource.updateAccommodation(accommodation.getId(), accommodationDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(accommodationService, never()).save(any());
    }

    @Test
    void shouldGetByIdSuccess() {
                when(accommodationService.findById(accommodation.getId())).thenReturn(Optional.of(accommodation));

        ResponseEntity<Object> response = accommodationResource.getById(accommodation.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof Accommodation);
    }

    @Test
    void shouldGetByIdNotFound() {
        when(accommodationService.findById(accommodation.getId())).thenReturn(Optional.empty());

        ResponseEntity<Object> response = accommodationResource.getById(accommodation.getId());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Acomodação não encontrada para esse id", response.getBody());
    }

}