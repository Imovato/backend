package com.example.acquisition.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Acquisition {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@DateTimeFormat(pattern = "MM/dd/yyy")
	@Column(name = "data", nullable = false)
	private Date data;

	@Column(name = "value", nullable = false)
	private Double value;

	@Column(name = "amount", length = 10)
	private Integer amount;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_property")
	private Property property;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user")
	private User user;
	
	public Acquisition() {
	}

	public Acquisition(Long id, Date data, Double value, Integer amount,Property property,User user) {
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
}
