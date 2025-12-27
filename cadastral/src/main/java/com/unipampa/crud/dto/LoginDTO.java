package com.unipampa.crud.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(

        String username,

        @NotBlank
        String password,

        @NotBlank
        String email

) {}
