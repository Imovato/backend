package com.unipampa.crud.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class JwtDTO {

    @NotNull
    private final String token;

    private final String type = "Bearer";
}
