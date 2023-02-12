package com.example.acquisition.model;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Builder;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Builder
public class Acquisition implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@DateTimeFormat(pattern = "MM/dd/yyy")
	@Column(name = "data", nullable = false)
	private LocalDate data;

	@Column(name = "value", nullable = false)
	private Double value;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_property")
	private Property property;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_user")
	private User user;
	
	public Acquisition() {
	}

	public Acquisition(Long id, LocalDate data, Double value,Property property,User user) {
		this.id = id;
		this.data = data;
		this.value = value;
		this.property = property;
		this.user = user;
	}
	
	public Long getId() {
		return id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate localDate) {
		this.data = localDate;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
}
