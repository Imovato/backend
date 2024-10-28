package com.example.rent.util;

import com.example.rent.enums.Status;
import com.example.rent.entities.Customer;
import com.example.rent.entities.Property;
import com.example.rent.entities.Rent;
import com.example.rent.entities.composite.PersonalInformation;

import java.time.LocalDate;

public class RentCreator {

    public static Rent createRentToSaved() {
        return Rent.builder()
                .startDateRent(LocalDate.now())
                .customer(createCustomer())
                .value(1250D)
                .build();
    }

    public static Rent createValidRent() {
        return Rent.builder()
                .id(1L)
                .startDateRent(LocalDate.now())
                .customer(createCustomer())
                .property(createProperty())
                .value(1250D)
                .build();
    }

    public static Rent createValidUpdateRent() {
        return Rent.builder()
                .id(1L)
                .startDateRent(LocalDate.now())
                .customer(createCustomer())
                .value(1777D)
                .build();
    }

    public static Customer createCustomer() {
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
    public static Property createProperty() {
        return Property.builder()
                .id(1L)
                .status(Status.AVAILABLE)
                .build();
    }
}
