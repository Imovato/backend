package com.unipampa.crud.dto;

import java.sql.Date;


import com.unipampa.crud.model.Property;

public class AcquisitionDTO {
	
	private long id;
	private long property_id;
	private double price;
	private Date date;
	static final double ITBI = 0.02; //ITBI = 2%
	
	public AcquisitionDTO(long id, Long property_id, double price, Date date) {
		this.id = id;
		this.property_id = property_id;
		this.price = price;
		this.date = date;
	}
	public AcquisitionDTO() {
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Long getProperty_id() {
		return property_id;
	}
	public void setProperty_id(Long property_id) {
		this.property_id = property_id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public static double getItbi() {
		return ITBI;
	}
	
}
