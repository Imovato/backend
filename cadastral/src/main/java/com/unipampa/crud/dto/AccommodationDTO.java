package com.unipampa.crud.dto;

import com.unipampa.crud.enums.AccommodationType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public record AccommodationDTO(
		@NotBlank String title,
		String neighborhood,
		String codAddress,
		@NotNull String city,
		@NotBlank String description,
		String adress,
		@NotBlank String state,
		@NotBlank BigDecimal price,
		int number,
		@NotNull int imageQuantity,
		@NotNull AccommodationType accommodationType
) {}
