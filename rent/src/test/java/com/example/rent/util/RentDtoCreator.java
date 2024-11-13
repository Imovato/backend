package com.example.rent.util;

import com.example.rent.dto.RentDto;
import com.example.rent.enums.Status;
import com.example.rent.entities.Accommodation;

import java.time.LocalDate;

public class RentDtoCreator {

    public static RentDto createRentDto() {
        return RentDto.builder()
                .startDateRent(LocalDate.now())
                .idUser(createCustomer().getId())
                .idAccommodation(createProperty().getId())
                .value(RentCreator.createRentToSaved().getValue())
                .build();
    }
    public static Customer createCustomer() {
        return Customer.builder()
                .id(1L)
                .personalInformation(createPersonal())
                .build();
    }

    public static PersonalInformation createPersonal() {
        return PersonalInformation.builder()
                .id(1L)
                .name("Pedro")
                .cpf("04408178035")
                .build();
    }
    public static Accommodation createProperty() {
        return Accommodation.builder()
                .id(1L)
                .salesman("Júlia")
                .status(Status.AVAILABLE)
                .build();
    }
}
