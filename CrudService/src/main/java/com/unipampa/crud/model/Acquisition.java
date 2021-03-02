package com.unipampa.crud.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_acquisitions")
public class Acquisition {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "property_id")
	private long property_id;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "date")
	private Date date;
	
	static final double ITBI = 0.02; //ITBI = 2%
	
	public Acquisition() {
		
	}
	
	public Acquisition(Property property) {
		this.property_id = property.getIdProperty();
	}

	public double calculatePriceAfterTaxes() {
		return price + (price * ITBI);
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

	public long getProperty_id() {
		return property_id;
	}

	public void setProperty_id(long property_id) {
		this.property_id = property_id;
	}
	
}
