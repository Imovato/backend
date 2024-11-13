package com.example.rent.util;

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
