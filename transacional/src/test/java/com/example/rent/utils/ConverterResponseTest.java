package com.example.rent.utils;

import com.example.rent.dto.RentDto;
import com.example.rent.entities.Accommodation;
import com.example.rent.entities.Rent;
import com.example.rent.entities.User;
import com.example.rent.enums.UserType;
import com.example.rent.response.RentResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConverterResponseTest {

    private ConverterResponse converterResponse;
    private Accommodation accommodation;
    private RentDto rentDto;
    private Rent rent;
    private User user;

    @BeforeEach
    void setUp() {
        converterResponse = new ConverterResponse();

        accommodation = new Accommodation();
        accommodation.setId(1L);

        user = new User(2L, "Cooper", "cooper@gmail", UserType.GUEST);


        rentDto = new RentDto(
                accommodation,
                user,
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 1, 31)
        );

        rent = new Rent();
        rent.setId(10L);
        rent.setAccommodation(accommodation);
        rent.setDateRent(LocalDate.now());
        rent.setStartDateRent(LocalDate.of(2023, 1, 1));
        rent.setEndDateRent(LocalDate.of(2023, 1, 31));
        rent.setPrice(1500.00);
        rent.setNumberAdults(2);
        rent.setNumberChild(1);
        rent.setNumberBaby(0);
        rent.setNumberPets(1);
        rent.setUserCancel(false);
        rent.setDateCancel(null);
        rent.setReasonCancellation(null);
        rent.setCancellationFee(0.0);
        rent.setRefund(true);
        rent.setRefundValue(1500.00);
    }

    @Test
    void testConvertToRentResponseListSuccess() {
        List<RentDto> rentDtos = List.of(rentDto, rentDto);

        List<RentResponse> rentResponses = converterResponse.convertToRentResponseList(rentDtos);

        assertNotNull(rentResponses);
        assertEquals(2, rentResponses.size());
        assertEquals(rentDto.accommodation(), rentResponses.get(0).accommodation());
        assertEquals(rentDto.startDateRent(), rentResponses.get(0).startDateRent());
        assertEquals(rentDto.endDateRent(), rentResponses.get(0).endDateRent());
    }

    @Test
    void testConvertToRentResponseListEmptyList() {
        List<RentResponse> rentResponses = converterResponse.convertToRentResponseList(Collections.emptyList());

        assertNotNull(rentResponses);
        assertTrue(rentResponses.isEmpty());
    }

    @Test
    void convertToRentResponse_Success() {
        RentResponse response = converterResponse.convertToRentResponse(rent);

        assertNotNull(response);
        assertEquals(rentDto.accommodation(), response.accommodation());
        assertEquals(rentDto.startDateRent(), response.startDateRent());
        assertEquals(rentDto.endDateRent(), response.endDateRent());
    }

    @Test
    void convertToRentResponse_NullRent() {
        assertThrows(NullPointerException.class, () -> converterResponse.convertToRentResponse(null));
    }

}