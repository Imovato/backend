package com.unipampa.crud.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Apartment extends Property {

	@Column(name = "block")
	private String block;

	public Apartment() {
		super();
	}

	public Apartment(Long number, String block) {
		super();
		this.block = block;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

}
