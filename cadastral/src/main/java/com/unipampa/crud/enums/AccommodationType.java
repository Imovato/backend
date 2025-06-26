package com.unipampa.crud.enums;

import io.swagger.v3.oas.annotations.media.Schema;

public enum AccommodationType {

    @Schema(description = "Apartamento")
    APARTMENT,

    @Schema(description = "Casa")
    HOUSE;
}
