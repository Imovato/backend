package com.example.auth.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;


@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Size(min = 2, max = 255, message = "Tamanho mínimo do nome: 2 caracteres")
	@Column(nullable = false)
	private String username;
	
	@Column(unique = true, nullable = false)
	private String email;

	@Size(min = 8, message = "Tamanho mínimo da senha: 8 caracteres")
	private String password;

	@ElementCollection(fetch = FetchType.EAGER)
  List<Role> roles;
	
	public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

	public List<Role> getRoles(){
		return roles;
	}

	public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

}
