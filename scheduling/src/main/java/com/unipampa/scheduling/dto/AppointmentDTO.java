package com.unipampa.scheduling.dto;

import java.time.LocalDateTime;

import com.unipampa.scheduling.model.Customer;
import com.unipampa.scheduling.model.Property;

public class AppointmentDTO {
	
	private Long id;
	private LocalDateTime date;
	private Property property;
	private Customer customer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}
