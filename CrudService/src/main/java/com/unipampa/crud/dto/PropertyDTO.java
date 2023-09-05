package com.unipampa.crud.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class PropertyDTO {

	private Long idProperty;
	private String name;
	private float area;
	private String neighborhood;
	private String codAddress;
	private String city;
	private String description;
	private String adress;
	private String state;
	private double price;
	private Long number;
	private Long rooms;
	private String block;
	private int amount;
	private int imageQuantity;

	public PropertyDTO() {

	}
	@Builder
	public PropertyDTO(Long idProperty, String adress, String name, String city){
		this.idProperty = idProperty;
		this.adress = adress;
		this.name = name;
		this.city = city;
	}

}
