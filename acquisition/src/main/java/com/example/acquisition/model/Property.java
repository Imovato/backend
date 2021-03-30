package com.example.acquisition.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Property {

	@Id
	private Long id;

	@Column(name = "amount", length = 10)
	private Integer amount;

	public Long getId() {
		return id;
	}

	@Column(name = "price")
	private double price;

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
