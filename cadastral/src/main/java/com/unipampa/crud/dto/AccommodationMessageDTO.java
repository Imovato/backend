package com.unipampa.crud.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccommodationMessageDTO {
    private BigDecimal price;
    private int maxOccupancy;
}
