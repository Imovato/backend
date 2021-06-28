package com.unipampa.crud.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
// import com.unipampa.crud.model.Role;

@RequiredArgsConstructor
@Accessors(fluent = true)
@Getter
public class CompleteUserDTO {

	@ApiModelProperty(position = 0)
	private final @NonNull String username;
	@ApiModelProperty(position = 1)
	private final @NonNull String email;
	@ApiModelProperty(position = 2)
	private final @NonNull String password;
	@ApiModelProperty(position = 3)
	private final @NonNull String cpf;
	@ApiModelProperty(position = 4)
	private final @NonNull String phone;
	@ApiModelProperty(position = 5)
	private final @NonNull String address;
	// @ApiModelProperty(position = 6)
	// List<Role> roles;
}