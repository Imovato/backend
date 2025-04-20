package com.unipampa.crud.dto;

import com.unipampa.crud.enums.AccommodationType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public record AccommodationDTO(
		@NotBlank String title,
		String neighborhood,
		String codAddress,
		@NotNull String city,
		@NotBlank String description,
		String address,
		@NotBlank String state,
		@NotBlank BigDecimal price,
		int streetNumber,
		@NotNull int imageQuantity,
		@NotNull AccommodationType accommodationType,
		@NotNull int maxOccupancy,
		List<String> imagesUrls
) {}
