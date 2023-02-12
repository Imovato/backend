package com.example.rent.util;

import com.example.rent.dto.RentDtoUpdate;

public class RentDtoUpdateCreator {

    public static RentDtoUpdate createRentDtoUpdate() {
        return RentDtoUpdate.builder()
                .id(RentCreator.createValidUpdateRent().getId())
                .value(RentCreator.createValidUpdateRent().getValue())
                .build();
    }
}
