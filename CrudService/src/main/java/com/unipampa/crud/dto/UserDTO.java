package com.unipampa.crud.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Accessors(fluent = true)
@Getter
public class UserDTO {

	private final @NonNull Long id;
	private final @NonNull String email;
	private final @NonNull String name;
	private final @NonNull String password;
	private final @NonNull String cpf;
	private final @NonNull String phone;
	private final @NonNull String address;
}
