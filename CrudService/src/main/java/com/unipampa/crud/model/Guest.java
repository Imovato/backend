package com.unipampa.crud.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class Guest extends User {

//    @Column(name = "phone", length = 10)
    private String phone;

//    @Column(name = "address", length = 200)
    private String address;

}
