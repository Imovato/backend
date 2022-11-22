package com.unipampa.crud.model;

import lombok.Builder;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Entity @SuperBuilder
public class Employee extends User {

	public Employee(String email, String name, String password) {
		super(email, name, password);
	}

	public Employee() {
		super();
	}
}
