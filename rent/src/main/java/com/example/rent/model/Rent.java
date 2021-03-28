package com.example.rent.model;



import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Rent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@DateTimeFormat(pattern = "MM/dd/yyy")
	@Column(name = "data", nullable = false)
	private Date data;

	@OneToOne(cascade = CascadeType.ALL)
	private Property propertie;

	@Column(name = "value", nullable = false)
	private Double value;
	
	public Rent() {
	}

	public Rent(Long id, Date data, Property propertie, Double value) {
		this.id = id;
		this.data = data;
		this.propertie = propertie;
		this.value = value;
	}

	public Long getId() {
		return id;
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

	public void setPropertie(Property properties) {
		this.propertie = properties;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
}
