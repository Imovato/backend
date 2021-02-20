package com.unipampa.crud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_user_registration")
public class User {
	
	@Id
	@Column(name = "id_user")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUser;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "name")
	private String name;

	public User(String email, String name) {
		this.email = email;
		this.name = name;
	}

	public User() {
		
	}
	
	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
