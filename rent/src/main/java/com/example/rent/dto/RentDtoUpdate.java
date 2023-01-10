package com.example.rent.dto;

import lombok.*;

import java.time.LocalDate;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class RentDtoUpdate {

    private Long id_rent;
    private LocalDate dateAdjustmentIGPM;
    private Double iptu;
    private Double water;
    private Double energy;
    private Double condominium;
    private Double value;
    private String description;

}
