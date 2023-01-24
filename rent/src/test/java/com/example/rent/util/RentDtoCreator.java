package com.example.rent.util;

import com.example.rent.dto.RentDto;
import com.example.rent.model.Customer;
import com.example.rent.model.composite.PersonalInformation;

import java.time.LocalDate;

public class RentDtoCreator {

    public static RentDto createRentDto() {
        return RentDto.builder()
                .startDateRent(LocalDate.now())
                .id_customer(createCustomer().getId())
                .value(RentCreator.createRentToSaved().getValue())
                .build();
    }
    private static Customer createCustomer() {
        return Customer.builder()
                .id(1L)
                .personalInformation(createPersonal())
                .build();
    }

    private static PersonalInformation createPersonal() {
        return PersonalInformation.builder()
                .id(1L)
                .name("Pedro")
                .cpf("04408178035")
                .build();
    }
}
