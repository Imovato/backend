package com.unipampa.scheduling.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Accessors(fluent = true)
@Getter
public class CustomerDTO {

	private final Long id;
	private final @NonNull String email;
	private final @NonNull String name;
	private final @NonNull String phone;
}