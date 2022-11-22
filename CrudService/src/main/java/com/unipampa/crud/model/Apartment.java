package com.unipampa.crud.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data @AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class Apartment extends Property {

    @Column(name = "block")
    private String block;

    public Apartment() {
        super();
    }
    public Apartment(Long number, String block) {
        super();
        this.block = block;
    }
}
