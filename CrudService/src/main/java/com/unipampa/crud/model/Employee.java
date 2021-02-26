package com.unipampa.crud.model;

import javax.persistence.Entity;

@Entity
public class Employee extends User {
	public Employee(String email, String name, String password) {
		super(email, name, password);
	}
	
	public Employee() {
		super();
	}
}
