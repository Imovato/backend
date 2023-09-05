package com.unipampa.crud.model;

import lombok.Builder;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Entity
@Builder
public class Ground extends Hosting {

    public Ground() {
        super();
    }
}
