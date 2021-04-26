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

	@Column(name = "value", nullable = false)
	private Double value;

	private Integer amount;

	@OneToOne(cascade = CascadeType.ALL)
	private Property property;

	@OneToOne(cascade = CascadeType.ALL)
	private  User user;

	public Rent() {
	}

	public Rent(Long id, Date data, Double value, Integer amount, Property property, User user) {
		this.id = id;
		this.data = data;
		this.value = value;
		this.amount = amount;
		this.property = property;
		this.user = user;
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

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
