package com.unipampa.crud.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
public class Guest extends User {

    @Column(name = "phone", length = 10)
    private String phone;

    @Column(name = "address", length = 200)
    private String address;

}
