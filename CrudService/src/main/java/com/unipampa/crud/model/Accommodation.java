package com.unipampa.crud.model;

import com.unipampa.crud.enums.AccommodationType;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Document
@Data
public class Accommodation {

    @Id
    private String id;

//    @Column(name = "title", length = 150, nullable = false)
    private String title;

//    @Column(name = "description", length = 500, nullable = false)
    private String description;

//    @Column(name = "neighborhood", length = 50)
    private String neighborhood;

//    @Column(name = "cod_address", length = 50)
    private String codAddress;

//    @Column(name = "city", length = 50, nullable = false)
    private String city;

//    @Column(name = "adress", length = 150)
//    @NotEmpty(message ="The address cannot be empty")
    private String adress;

//	@JoinColumn(name = "cd_situation")
//	private PropertySituation propertySituation;

//    @Column(name = "state", length = 2, nullable = false)
    private String state;

//    @Column(name = "price", nullable = false)
    private double price;

//    @Column(name = "number")
    private Long number;

//    @Column(name = "imageQuantity", nullable = false)
    private int imageQuantity;

//    @ManyToOne
//    private User user;

//    @Column(nullable = false)
    private AccommodationType accommodationType;

}
