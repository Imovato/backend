package com.unipampa.crud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_property_registration")
public abstract class Property {

	@Id
	@Column(name = "id_property")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProperty;

	@Column(name = "area_property")
	private float area;

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

//	@JoinColumn(name = "cd_situation")
//	private PropertySituation propertySituation;

	@Column(name = "state")
	private String state;

	@Column(name = "price")
	private double price;

	@Column(name = "number")
	private Long number;

	public Property() {

	}

	public Property(Long idProperty, float area, String neighborhood, String codAddress, String city,
			String description, String adress, String state, double price) {
		this.idProperty = idProperty;
		this.area = area;
		this.neighborhood = neighborhood;
		this.codAddress = codAddress;
		this.city = city;
		this.description = description;
		this.adress = adress;
		this.state = state;
		this.price = price;
	}

	public Long getIdProperty() {
		return idProperty;
	}

	public float getArea() {
		return area;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public String getCodAddress() {
		return codAddress;
	}

	public String getCity() {
		return city;
	}

	public String getDescription() {
		return description;
	}

	public String getAdress() {
		return adress;
	}

	public String getState() {
		return state;
	}

	public double getPrice() {
		return price;
	}

	public void setIdProperty(Long idProperty) {
		this.idProperty = idProperty;
	}

	public void setArea(float area) {
		this.area = area;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public void setCodAddress(String codAddress) {
		this.codAddress = codAddress;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

}
