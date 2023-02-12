package com.example.rent.dto;

import lombok.*;

import java.time.LocalDate;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class RentDtoUpdate {

    private Long id;
    private Double iptu;
    private Double water;
    private Double energy;
    private Double condominium;
    private Double value;
    private String description;

}
