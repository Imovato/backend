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
	private Property property;

	@Column(name = "value", nullable = false)
	private Double value;
	
	public Rent() {
	}

	public Rent(Long id, Date data, Property property, Double value) {
		this.id = id;
		this.data = data;
		this.property = property;
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

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property propertys) {
		this.property = propertys;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
}
