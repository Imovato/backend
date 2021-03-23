package com.unipampa.scheduling.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "tbl_appointments")
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@DateTimeFormat(pattern = "MM/dd/yyy")
	@Column(name = "date", nullable = false)
	private Date date;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Property property;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Customer customer;

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
	
	public long daysUntilAppointment() {
		Date currentDate = new Date();
		long daysUntil = (date.getTime()-currentDate.getTime())/86400000;
		return daysUntil;
	}
	
	public boolean isAppointmentToday() {
		return 0 == daysUntilAppointment();
	}
}
