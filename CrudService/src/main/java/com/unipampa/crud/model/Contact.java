package com.unipampa.crud.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "tbl_property_contact")
@Builder @Data @AllArgsConstructor @NoArgsConstructor
public class Contact {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "message")
	private String message;

	@Column(name = "name")
	@NotEmpty(message = "The name cannot be empty")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "number")
	private String number;


	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getMessage() {
		return message;
	}

	public String getEmail() {
		return email;
	}

	public String getNumber() {
		return number;
	}

	public void setId(Long idProperty) {
		this.id = idProperty;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}