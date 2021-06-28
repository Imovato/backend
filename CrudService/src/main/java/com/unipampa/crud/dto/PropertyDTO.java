package com.unipampa.crud.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Accessors(fluent = true)
@Getter
public class PropertyDTO {

	private final Long idProperty;
	private final @NonNull String name;
	private final @NonNull float area;
	private final @NonNull String neighborhood;
	private final @NonNull String codAddress;
	private final @NonNull String city;
	private final @NonNull String description;
	private final @NonNull String adress;
	private final @NonNull String state;
	private final @NonNull double price;
	private final @NonNull Long number;
	private final @NonNull Long rooms;
	private final @NonNull String block;
	private final @NonNull int amount = 0;
	private final @NonNull int imageQuantity = 0;
}