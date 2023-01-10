package com.example.rent.dto;

import com.example.rent.model.Customer;
import com.example.rent.model.Property;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class RentDto {

    private Long id_property;
    private Long id_customer;
    private LocalDate startDateRent = LocalDate.now();
    private LocalDate endDateRent;
    @NotEmpty(message = "The start date rent cannot be empty")
    private LocalDate dateAdjustmentIGPM;
    private Double iptu;
    private Double water;
    private Double energy;
    private Double condominium;
    private Double value;
    private String description;
}
