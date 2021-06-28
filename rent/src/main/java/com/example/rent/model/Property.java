package com.example.rent.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Property {

	@Id
	private Long id;

	@Column(name = "amount", length = 10)
	private Integer amount;

	@Column(name = "price")
	private double price;

	@Column
	private String status;
}