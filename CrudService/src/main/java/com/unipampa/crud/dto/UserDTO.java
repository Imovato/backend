package com.unipampa.crud.dto;

import lombok.Data;

@Data
public class UserDTO {

	private Long id;
	private String email;
	private String name;
	private String password;
	private String cpf;
	private String phone;
	private String address;

}