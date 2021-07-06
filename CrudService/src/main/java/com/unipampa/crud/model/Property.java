package com.unipampa.crud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.unipampa.crud.dto.PropertyDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tbl_property_registration")
public class Property {

	@Column(insertable = false, updatable = false)
	private String dtype;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "area_property")
	private float area;
	@Column(name = "name")
	private String name;
	@Column(name = "neighborhood")
	private String neighborhood;
	@Column(name = "cod_address")
	private String codAddress;
	@Column(name = "city")
	private String city;
	@Column(name = "description")
	private String description;
	@Column(name = "adress")
	private String adress;
	// @JoinColumn(name = "cd_situation")
	// private PropertySituation propertySituation;
	@Column(name = "state")
	private String state;
	@Column(name = "price")
	private double price;
	@Column(name = "number")
	private Long number;
	@Column(name = "rooms")
	private Long rooms;
	@Column(name = "amount")
	private int amount = 0;
	@Column(name = "imageQuantity")
	private int imageQuantity = 0;
	@Column(name = "virtualImageQuantity")
	private int virtualImageQuantity = 0;
	@ManyToOne
	private User user;

}