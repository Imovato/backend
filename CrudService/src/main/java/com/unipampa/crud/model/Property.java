package com.unipampa.crud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_property_registration")
public class Property {

	@Column(insertable = false, updatable = false) private String dtype;

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

//	@JoinColumn(name = "cd_situation")
//	private PropertySituation propertySituation;

	@Column(name = "state")
	private String state;

	@Column(name = "price")
	private double price;

	@Column(name = "number")
	private Long number;

	@Column(name = "rooms")
	private Long rooms;
	
	@Column(name = "amount")
	private int amount;
	
	@ManyToOne
	private User user;
	
	public Property() {

	}

	public Property(Long idProperty, String name, float area, String neighborhood, String codAddress, String city,
			String description, String adress, String state, double price, Long rooms ) {
		this.id = idProperty;
		this.name = name;
		this.area = area;
		this.neighborhood = neighborhood;
		this.codAddress = codAddress;
		this.city = city;
		this.description = description;
		this.adress = adress;
		this.state = state;
		this.price = price;
		this.rooms = rooms;
	}

	public Long getId() {
		return id;
	}

	public float getArea() {
		return area;
	}

	public String getName() {
		return name;
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

	public Long getRooms() {
		return rooms;
	}

	public void setId(Long idProperty) {
		this.id = idProperty;
	}

	public void setName(String name) {
		this.name = name;
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

	public void setRooms(Long rooms) {
		this.rooms = rooms;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
