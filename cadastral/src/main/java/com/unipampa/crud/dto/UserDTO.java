package com.unipampa.crud.dto;


import com.unipampa.crud.enums.UserType;
import jakarta.validation.constraints.NotBlank;

public record UserDTO(
        @NotBlank String email,
        @NotBlank String name,
        @NotBlank String userName,
        @NotBlank String cpf,
        @NotBlank String password,
        @NotBlank String role,
        @NotBlank UserType type,
        String phone,
        String address
) {
}