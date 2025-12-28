package com.unipampa.crud.dto;

import com.unipampa.crud.enums.AccommodationType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

public record AccommodationFilterDTO(
		@Schema(example = "Porto Alegre", description = "Cidade da acomodação")
		String city,

		@Schema(example = "RS", description = "Estado/UF da acomodação")
		String state,

		@Schema(example = "Centro Histórico", description = "Bairro da acomodação")
		String neighborhood,

		@Schema(example = "500.00", description = "Preço mínimo da acomodação")
		BigDecimal priceMin,

		@Schema(example = "5000.00", description = "Preço máximo da acomodação")
		BigDecimal priceMax,

		@Schema(example = "APARTMENT", description = "Tipo de acomodação")
		AccommodationType accommodationType,

		@Schema(example = "4", description = "Ocupância máxima mínima")
		Integer maxOccupancyMin,

		@Schema(example = "true", description = "Permite animais de estimação")
		Boolean allowsPets,

		@Schema(example = "true", description = "Permite crianças")
		Boolean allowsChildren,

		@Schema(example = "true", description = "É um alojamento compartilhado")
		Boolean isSharedHosting
) {}

