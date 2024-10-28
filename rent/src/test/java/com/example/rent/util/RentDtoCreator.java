package com.example.rent.util;

import com.example.rent.dto.RentDto;
import com.example.rent.enums.Status;
import com.example.rent.entities.Customer;
import com.example.rent.entities.Property;
import com.example.rent.entities.composite.PersonalInformation;

import java.time.LocalDate;

public class RentDtoCreator {

    public static RentDto createRentDto() {
        return RentDto.builder()
                .startDateRent(LocalDate.now())
                .id_customer(createCustomer().getId())
                .id_property(createProperty().getId())
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
    public static Property createProperty() {
        return Property.builder()
                .id(1L)
                .salesman("Júlia")
                .status(Status.AVAILABLE)
                .build();
    }
}
