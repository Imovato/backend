package com.example.rent.dto;


import java.util.Date;

import com.example.rent.model.Property;

public class RentDTO {
	
	private Long id;
	private Date data;
	private Property property;
	private Double amountValue;

	public RentDTO() {
	}

	public RentDTO(Long id, Date data, Property property, Double amountValue) {
		this.id = id;
		this.data = data;
		this.property = property;
		this.amountValue = amountValue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public Double getAmountValue() {
		return amountValue;
	}

	public void setAmountValue(Double amountValue) {
		this.amountValue = amountValue;
	}
}
