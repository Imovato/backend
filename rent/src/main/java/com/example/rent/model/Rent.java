package com.example.rent.model;

import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Rent {

	@Id
	private Long id;

	// @DateTimeFormat(pattern = "MM/dd/yyy")
	// @Column(name = "data", nullable = false)
	// private Date data;

	@Column(name = "value", nullable = false)
	private Double value;

	private Integer amount;

	@OneToOne(cascade = CascadeType.ALL)
	private Property property;

	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
}