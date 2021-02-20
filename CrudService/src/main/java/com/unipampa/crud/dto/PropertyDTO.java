package com.unipampa.crud.dto;

public class PropertyDTO {

	private Long idProperty;
	private float area;
	private String neighborhood;
	private String codAddress;
	private String city;
	private String description;
	private String adress;
	private String state;
	private double price;
	private Long number;
	private String block;

	public PropertyDTO() {

	}

	public PropertyDTO(Long idProperty, float area, String neighborhood, String codAddress, String city,
			String description, String adress, String state, double price, Long number, String block) {
		this.idProperty = idProperty;
		this.area = area;
		this.neighborhood = neighborhood;
		this.adress = adress;
		this.codAddress = codAddress;
		this.city = city;
		this.description = description;
		this.state = state;
		this.price = price;
		this.number = number;
		this.block = block;
	}

	public Long getIdProperty() {
		return idProperty;
	}

	public void setIdProperty(Long idProperty) {
		this.idProperty = idProperty;
	}

	public float getArea() {
		return area;
	}

	public void setArea(float area) {
		this.area = area;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getCodAddress() {
		return codAddress;
	}

	public void setCodAddress(String codAddress) {
		this.codAddress = codAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long numberHouse) {
		this.number = numberHouse;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

}
