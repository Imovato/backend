package com.unipampa.crud.entities;

import com.unipampa.crud.enums.AccommodationType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;
import java.math.BigDecimal;
import java.util.List;

@Builder
@Document
@Data
public class Accommodation {

    @Id
    private String id;
    private String title;
    private String description;
    private BigDecimal price;
    private List<String> imagesUrls;
    private int roomCount;
    private int bathroomCount;
    private boolean allowsPets;
    private boolean allowsChildren;
    private boolean isSharedHosting;
    private boolean isAuthorizedAnnouncement;
    private AccommodationType type;
    private String city;
    private String neighborhood;
    private int streetNumber;
    private String zipCode;
    private String address;
    private String state;
    private int imageCount;
    private int maxOccupancy;

}
