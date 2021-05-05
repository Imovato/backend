package com.unipampa.crud.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

// import com.unipampa.crud.model.Role;

public class CompleteUserDTO {

	@ApiModelProperty(position = 0)
	private String username;
	@ApiModelProperty(position = 1)
	private String email;
	@ApiModelProperty(position = 2)
	private String password;
	@ApiModelProperty(position = 3)
	private String cpf;
	@ApiModelProperty(position = 4)
	private String phone;
	@ApiModelProperty(position = 5)
	private String address;

	@ApiModelProperty(position = 6)
	// List<Role> roles;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	// public List<Role> getRoles() {
	// return roles;
	// }

	// public void setRoles(List<Role> roles) {
	// this.roles = roles;
	// }

}
