package com.example.rent.utils;

import com.example.rent.dto.RentDto;
import com.example.rent.response.RentResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConverterResponse {

    public List<RentResponse> convertToRentResponse(List<RentDto> rents) {
        return rents.stream().map(rent -> new RentResponse(
                rent.accommodation(),
                rent.startDateRent(),
                rent.endDateRent()
        )).collect(Collectors.toList());

    }
}
