package com.unipampa.crud.model;

import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Entity @SuperBuilder
public class Ground extends Property {

    public Ground() {
        super();
    }
}
