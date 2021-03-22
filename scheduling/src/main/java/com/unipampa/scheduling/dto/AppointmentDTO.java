package com.unipampa.scheduling.dto;

import java.util.Date;

public class AppointmentDTO {
	
	private Long id;
	private Date date;
	private PropertyDTO property;
	private CustomerDTO customer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public PropertyDTO getProperty() {
		return property;
	}

	public void setProperty(PropertyDTO property) {
		this.property = property;
	}

	public CustomerDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}
	
	
}
