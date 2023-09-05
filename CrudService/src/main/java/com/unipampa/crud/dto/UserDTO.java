package com.unipampa.crud.dto;

import com.unipampa.crud.enums.UserType;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserDTO {

	@NotBlank
	private String email;

	@NotBlank
	private String name;

	@NotBlank
	private String cpf;

	@NotBlank
	private String phone;

	@NotBlank
	private String address;

	private UserType type;

}