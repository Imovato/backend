package com.unipampa.crud.model;

import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Entity
@SuperBuilder(toBuilder = true)
public class Ground extends Hosting {

    public Ground() {
        super();
    }
}
