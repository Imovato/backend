package com.example.auth.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import com.example.auth.model.Role;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@JsonDeserialize(builder = CompleteUserDTO.CompleteUserDTOBuilder.class)
@RequiredArgsConstructor
@Accessors(fluent = true)
@Getter
@Builder
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
	@ApiModelProperty(position = 6)
	private final @NonNull List<Role> roles;

	@JsonPOJOBuilder(withPrefix = "")
	public static class CompleteUserDTOBuilder {

	}
}