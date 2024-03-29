package com.example.acquisition.model;


import com.example.acquisition.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@SuperBuilder
public class Property {

	@Id
	private Long id;

	@Column(name = "price")
	private double price;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private Status status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Status getStatus() { return status; }

	public void setStatus(Status status) { this.status = status; }
}
