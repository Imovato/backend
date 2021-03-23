package com.unipampa.crud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Null;

@Entity
@Table(name = "tbl_property_contact")
public class Contact {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "message")
	private String message;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "number")
	private String number;

    public Contact() {

	}

	public Contact(String name, String message, String email, String number) {
		this.name = name;
		this.message = message;
		this.email = email;
		this.number = number;
	}

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