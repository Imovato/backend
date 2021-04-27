package com.example.payment.dto;

import java.util.Date;

import com.example.payment.model.Property;

public class RentDTO {

	private Long id;
	private Date data;
	private Property propertie;
	private Double amountValue;

	public RentDTO() {
	}

	public RentDTO(Long id, Date data, Property propertie, Double amountValue) {
		this.id = id;
		this.data = data;
		this.propertie = propertie;
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

	public Property getPropertie() {
		return propertie;
	}

	public void setPropertie(Property propertie) {
		this.propertie = propertie;
	}

	public Double getAmountValue() {
		return amountValue;
	}

	public void setAmountValue(Double amountValue) {
		this.amountValue = amountValue;
	}
}
