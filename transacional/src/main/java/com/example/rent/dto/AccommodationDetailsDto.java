package com.example.rent.dto;

import java.math.BigDecimal;
import java.util.List;

public record AccommodationDetailsDto(
        String id,
        String title,
        String neighborhood,
        String codAddress,
        String city,
        String description,
        String address,
        String state,
        BigDecimal price,
        Integer streetNumber,
        Integer imageQuantity,
        String accommodationType,
        Integer maxOccupancy,
        Integer roomCount,
        Integer bathroomCount,
        Boolean allowsPets,
        Boolean isSharedHosting,
        List<String> imagesUrls
) {
}

