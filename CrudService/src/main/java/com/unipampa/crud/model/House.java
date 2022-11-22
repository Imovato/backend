package com.unipampa.crud.model;

import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Entity
@SuperBuilder(toBuilder = true)
public class House extends Property {
	public House() {
		super();
	}

}
