package com.unipampa.crud.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Accessors(fluent = true)
@Getter
public class ContactDTO {

	private final Long id;
	private final @NonNull String message;
	private final @NonNull String name;
	private final @NonNull String email;
	private final @NonNull String number;
}