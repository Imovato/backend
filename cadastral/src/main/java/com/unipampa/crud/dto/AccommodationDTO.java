package com.unipampa.crud.dto;

import com.unipampa.crud.enums.AccommodationType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record AccommodationDTO(

		String id,

		@NotBlank
		@Schema(example = "Apartamento moderno no centro 2")
		String title,

		@Schema(example = "Centro Histórico")
		String neighborhood,

		@Schema(example = "1234XYZ")
		String codAddress,

		@NotNull
		@Schema(example = "Porto Alegre")
		String city,

		@NotBlank
		@Schema(example = "Apartamento bem iluminado, com 3 quartos e cozinha equipada.")
		String description,

		@Schema(example = "Rua dos Andradas")
		String address,

		@NotBlank
		@Schema(example = "RS")
		String state,

		@NotNull
		@Schema(example = "2500.00")
		BigDecimal price,

		@Schema(example = "123")
		int streetNumber,

		@NotNull
		@Schema(example = "2")
		Integer imageQuantity,

		@NotNull
		@Schema(example = "APARTMENT")
		AccommodationType accommodationType,

		@NotNull
		@Schema(example = "4")
		Integer maxOccupancy,

		@NotNull
		@Schema(example = "2")
		Integer roomCount,

		@NotNull
		@Schema(example = "1")
		Integer bathroomCount,

		@NotNull
		@Schema(example = "true")
		Boolean allowsPets,

		@NotNull
		@Schema(example = "true")
		Boolean isSharedHosting,

		@Schema(example = "[]")
		List<String> imagesUrls
) {}
