package com.example.payment.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@Entity
public class Rent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRent;

	@DateTimeFormat(pattern = "MM/dd/yyy")
	@Column(name = "data", nullable = false)
	private Date data;

	@OneToOne(cascade = CascadeType.ALL)
	private Property propertie;

	@Column(name = "value", nullable = false)
	private Double value;
	
	public Rent() {
	}

	public Rent(Long idRent, Date data, Property propertie, Double value) {
		this.idRent = idRent;
		this.data = data;
		this.propertie = propertie;
		this.value = value;
	}

}
