package com.unipampa.crud.model;

import com.unipampa.crud.enums.AccommodationType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.math.BigDecimal;

@Builder
@Document
@Data
public class Accommodation {

    @Id
    private String id;
    private String title;
    private String description;
    private String neighborhood;
    private String codAddress;
    private String city;
    private String adress;
    private String state;
    private BigDecimal price;
    private int number;
    private int imageQuantity;
    private AccommodationType accommodationType;

}
