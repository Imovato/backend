package com.example.rent.service.impl;

import com.example.rent.dto.RentDto;
import com.example.rent.entities.Accommodation;
import com.example.rent.entities.Rent;
import com.example.rent.entities.User;
import com.example.rent.enums.Status;
import com.example.rent.repository.RentRepository;
import com.example.rent.response.RentResponse;
import com.example.rent.service.UserService;
import com.example.rent.utils.ConverterResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RentServiceImplTest {

    @Mock
    private RentRepository rentRepository;

    @Mock
    private UserService userService;

    @Mock
    private ConverterResponse converterResponse;

    @InjectMocks
    private RentServiceImpl rentService;

    private User user;
    private RentDto rentDto;

    private Accommodation accommodation;

    @BeforeEach
    void setUp() {
        accommodation = new Accommodation();
        accommodation.setId(10L);
        accommodation.setPrice(100.0);
        accommodation.setStatus(Status.AVAILABLE);
        user = new User();
        user.setId(1L);

        rentDto = new RentDto(accommodation, user, LocalDate.now(), LocalDate.now().plusDays(7));

    }

    @Test
    void shouldReturnRentListWhenUserHasRents() {


        List<RentDto> rentDtoList = List.of(
                new RentDto(accommodation, user, LocalDate.now(), LocalDate.now().plusDays(7)),
                new RentDto(accommodation, user, LocalDate.now().plusDays(1), LocalDate.now().plusDays(8))
        );

        List<RentResponse> expectedResponses = List.of(
                new RentResponse(accommodation, LocalDate.now(), LocalDate.now().plusDays(7)),
                new RentResponse(accommodation, LocalDate.now().plusDays(1), LocalDate.now().plusDays(8))
        );

        when(rentRepository.findByUserId(user.getId())).thenReturn(rentDtoList);
        when(converterResponse.convertToRentResponseList(rentDtoList)).thenReturn(expectedResponses);

        List<RentResponse> actualResponses = rentService.findByUserId(user.getId());

        assertNotNull(actualResponses);
        assertEquals(2, actualResponses.size());
        verify(rentRepository, times(1)).findByUserId(user.getId());
        verify(converterResponse, times(1)).convertToRentResponseList(rentDtoList);
    }

    @Test
    void shouldReturnEmptyListWhenUserHasNoRents() {
        when(rentRepository.findByUserId(user.getId())).thenReturn(Collections.emptyList());
        when(converterResponse.convertToRentResponseList(Collections.emptyList())).thenReturn(Collections.emptyList());

        List<RentResponse> actualResponses = rentService.findByUserId(user.getId());

        assertNotNull(actualResponses);
        assertTrue(actualResponses.isEmpty());
        verify(rentRepository, times(1)).findByUserId(user.getId());
        verify(converterResponse, times(1)).convertToRentResponseList(Collections.emptyList());
    }

    @Test
    void shouldReturnUserWhenFound() {
        when(userService.findById(user.getId())).thenReturn(Optional.of(user));
        User result = rentService.searchUserForRent(rentDto);

        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        when(userService.findById(user.getId())).thenReturn(Optional.of(user));
        when(userService.findById(rentDto.user().getId())).thenReturn(Optional.empty());

        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> rentService.searchUserForRent(rentDto)
        );

        assertEquals("Usuário não encontrado", thrown.getMessage());
    }

    @Test
    void shouldBuildRentSuccessfully() {

        RentDto dto = mock(RentDto.class);
        when(dto.startDateRent()).thenReturn(rentDto.startDateRent());

        Rent rent = rentService.buildRent(accommodation, user, dto);

        assertNotNull(rent);
        assertEquals(accommodation, rent.getAccommodation());
        assertEquals(user, rent.getUser());
        assertEquals(100.0, rent.getPrice());
        assertEquals(Status.RENTED, accommodation.getStatus());
        assertEquals(LocalDate.now(), rent.getDateRent());
    }

}