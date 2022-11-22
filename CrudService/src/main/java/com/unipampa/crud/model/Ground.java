package com.unipampa.crud.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class Ground extends Property {

    public Ground() {
        super();
    }
}
