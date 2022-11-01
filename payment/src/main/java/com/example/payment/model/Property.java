package com.example.payment.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Property {
	
	@Id
	private Long id;
	
	@Column(name = "amount", length = 10)
	private Integer amount;

}
