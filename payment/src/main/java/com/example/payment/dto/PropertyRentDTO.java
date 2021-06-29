package com.example.payment.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Accessors(fluent = true)
@Getter
public class PropertyRentDTO {

	private final Long id;
	private final @NonNull Long idProperty;
	private final @NonNull Integer amount;
}