package com.example.rent.resources;

import com.example.rent.dto.RentDto;
import com.example.rent.entities.Accommodation;
import com.example.rent.entities.User;
import com.example.rent.response.RentResponse;
import com.example.rent.service.RentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RentControllerTest {


    @Mock
    private RentService rentService;

    @InjectMocks
    private RentController rentController;

    private RentDto validRentDto;
    private RentResponse rentResponse;

    @BeforeEach
    void setUp() {

        Accommodation accommodation = new Accommodation();
        accommodation.setId(6L);
        accommodation.setPrice(100.0);

        User user = new User();
        user.setId(2L);
        user.setName("Cooper");

        validRentDto = new RentDto(
                accommodation,
                user,
                LocalDate.of(2023, 1, 17),
                LocalDate.of(2023, 1, 17)
        );

        rentResponse = new RentResponse(
                accommodation,
                LocalDate.of(2023, 1, 17),
                LocalDate.of(2023, 1, 17)
        );
    }


    @Test
    void testSaveSuccess() {
        when(rentService.createNewRent(validRentDto)).thenReturn(rentResponse);

        var response = rentController.save(validRentDto);

        assertNotNull(response);
        assertEquals(201, response.getStatusCode().value());
        assertEquals(rentResponse, response.getBody());
        verify(rentService, times(1)).createNewRent(validRentDto);
    }

    @Test
    void testFindRentsByUserIdSuccess() {
        Long userId = 2L;
        List<RentResponse> expectedRents = List.of(rentResponse);
        when(rentService.findByUserId(userId)).thenReturn(expectedRents);

        ResponseEntity<List<RentResponse>> response = rentController.findRentsByUserId(userId);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(expectedRents, response.getBody());
        verify(rentService, times(1)).findByUserId(userId);
    }

    @Test
    void testFindRentsByUserIdEmptyList() {
        Long userId = 3L;
        when(rentService.findByUserId(userId)).thenReturn(List.of());

        ResponseEntity<List<RentResponse>> response = rentController.findRentsByUserId(userId);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody().isEmpty());
        verify(rentService, times(1)).findByUserId(userId);
    }
}