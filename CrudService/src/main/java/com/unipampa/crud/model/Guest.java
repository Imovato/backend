package com.unipampa.crud.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity @Data @SuperBuilder
public class Guest extends User {

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    public Guest(String email, String name, String password, String cpf, String phone, String address) {
        super(email, name, password);
        this.cpf = cpf;
        this.phone = phone;
        this.address = address;
    }


    public Guest() {
        super();
    }
}
