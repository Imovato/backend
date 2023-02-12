package com.unipampa.crud.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Entity
@SuperBuilder(toBuilder = true)
public class Ground extends Property {

    public Ground() {
        super();
    }
}
