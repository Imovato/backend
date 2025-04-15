package com.unipampa.crud.dto;

import com.unipampa.crud.enums.UserType;
import lombok.Data;

import javax.validation.constraints.NotBlank;

public record UserDTO(
		@NotBlank String email,
		@NotBlank String name,
		@NotBlank String userName,
		@NotBlank String cpf,
		@NotBlank String phone,
		@NotBlank String address,
		UserType type
) {}