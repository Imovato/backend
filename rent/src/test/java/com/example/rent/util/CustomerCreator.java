package com.example.rent.util;

import com.example.rent.entities.Customer;
import com.example.rent.entities.composite.PersonalInformation;

public class CustomerCreator {
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
}
