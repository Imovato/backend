package com.unipampa.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder @AllArgsConstructor @NoArgsConstructor
public class ContactDTO {

	private Long id;
	private String message;
	private String name;
	private String email;
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