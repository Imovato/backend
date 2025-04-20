package com.unipampa.crud.dto;

import com.unipampa.crud.enums.AccommodationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public record AccommodationDTO(
		String id,
		@NotBlank String title,
		String neighborhood,
		String codAddress,
		@NotNull String city,
		@NotBlank String description,
		String address,
		@NotBlank String state,
		@NotNull BigDecimal price,
		int streetNumber,
		@NotNull Integer imageQuantity,
		@NotNull AccommodationType accommodationType,
		@NotNull Integer maxOccupancy,
		List<String> imagesUrls
) {}
