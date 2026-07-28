package com.unipampa.crud.dto;

public record PasswordResetResponseDTO(
        String message,
        String resetToken,
        String expiresAt
) {
}
