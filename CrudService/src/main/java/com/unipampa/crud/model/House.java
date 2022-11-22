package com.unipampa.crud.model;

import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Entity @SuperBuilder
public class House extends Property {
	public House() {
		super();
	}

}
