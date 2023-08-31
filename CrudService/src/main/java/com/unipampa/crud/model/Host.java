package com.unipampa.crud.model;

import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Entity @SuperBuilder
public class Host extends User {

	public Host(String email, String name, String password) {
		super(email, name, password);
	}

	public Host() {
		super();
	}
}
