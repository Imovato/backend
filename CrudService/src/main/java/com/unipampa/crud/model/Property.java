package com.unipampa.crud.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_property_registration")
@Data @SuperBuilder
public class Property {

    @Column(insertable = false, updatable = false)
    private String dtype;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "area_property")
    private float area;

    @Column(name = "name")
    private String name;

    @Column(name = "neighborhood")
    private String neighborhood;

    @Column(name = "cod_address")
    private String codAddress;

    @Column(name = "city")
    private String city;

    @Column(name = "description")
    private String description;

    @Column(name = "adress")
    private String adress;

//	@JoinColumn(name = "cd_situation")
//	private PropertySituation propertySituation;

    @Column(name = "state")
    private String state;

    @Column(name = "price")
    private double price;

    @Column(name = "number")
    private Long number;

    @Column(name = "rooms")
    private Long rooms;

    @Column(name = "amount")
    private int amount;

    @Column(name = "imageQuantity")
    private int imageQuantity;

    @ManyToOne
    private User user;

    public Property() {

    }

    public Property(Long idProperty, String name, float area, String neighborhood, String codAddress, String city,
                    String description, String adress, String state, double price, Long rooms) {
        this.id = idProperty;
        this.name = name;
        this.area = area;
        this.neighborhood = neighborhood;
        this.codAddress = codAddress;
        this.city = city;
        this.description = description;
        this.adress = adress;
        this.state = state;
        this.price = price;
        this.rooms = rooms;
        this.amount = 0;
        this.imageQuantity = 0;
    }
}
