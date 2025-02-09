package com.unipampa.crud.entities;

import com.unipampa.crud.enums.AccommodationType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;
import java.math.BigDecimal;

@Builder
@Document
@Data
public class Accommodation {

    @Id
    private String id;
    private String title;
    private String description;
    private BigDecimal price;
    private String imagesUrl;
    private int numberRoooms;
    private int numberBathrooms;
    private boolean acceptsAnimals;
    private boolean acceptsChildren;
    private boolean sharedHosting;
    private boolean authorizedAnnouncement;
    private AccommodationType accommodationType;
    private String city;
    private String neighborhood;
    private int number;
    private String zipCode;
    private String address;
    private String state;
    private int imageQuantity;

}
