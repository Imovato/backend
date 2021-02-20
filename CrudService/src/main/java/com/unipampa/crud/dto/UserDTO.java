package com.unipampa.crud.dto;

public class UserDTO {

	private Long idUser;
	private String email;
	private String name;
	private String cpf;
	private String phone;
	private String address;
	
	public UserDTO() {
		
	}

	public UserDTO(Long idUser, String email, String name, String cpf, String phone, String address) {
		super();
		this.idUser = idUser;
		this.email = email;
		this.name = name;
		this.cpf = cpf;
		this.phone = phone;
		this.address = address;
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
	
	
}
