package com.unipampa.crud.enums;

import io.swagger.v3.oas.annotations.media.Schema;

public enum AccommodationStats {

    @Schema(description = "Propriedade indisponível")
    UNAVAILABLE,

    @Schema(description = "Propriedade disponível")
    AVAILABLE;
}
