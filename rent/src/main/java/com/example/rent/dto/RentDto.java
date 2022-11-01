package com.example.rent.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.Date;

@Data @AllArgsConstructor
public class RentDto {

    private Long id;
    //private LocalDate startDateRent;
   // private LocalDate endDateRent;
    private Integer amount;
    private Double value;
    private Integer expirationDay;
    private Double iptu;
    private String description;
    private Long idCostumer;
    private Long idProperty;

}
