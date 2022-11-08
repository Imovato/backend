package com.example.rent.dto;

import lombok.*;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class RentDtoUpdate {

    private Double value;
    private Integer expirationDay;
    private Double iptu;
    private String description;

}
