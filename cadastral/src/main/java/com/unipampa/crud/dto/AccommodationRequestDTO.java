package com.unipampa.crud.dto;

import com.unipampa.crud.enums.AccommodationType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record AccommodationRequestDTO(
        @NotBlank
        @Schema(example = "Apartamento moderno no centro")
        String title,

        @Schema(example = "Centro Histórico")
        String neighborhood,

        @Schema(example = "1234XYZ")
        String codAddress,

        @NotNull
        @Schema(example = "Porto Alegre")
        String city,

        @NotBlank
        @Schema(example = "Apartamento bem iluminado, com 2 quartos e cozinha equipada.")
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

        @Schema(example = "[\"https://img.com/1.jpg\", \"https://img.com/2.jpg\"]")
        List<String> imagesUrls,

        @NotBlank
        String hostId
) {}
