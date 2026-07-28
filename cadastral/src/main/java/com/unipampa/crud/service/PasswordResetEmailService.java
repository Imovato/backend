package com.unipampa.crud.service;

import jakarta.mail.MessagingException;

import java.time.LocalDateTime;

public interface PasswordResetEmailService {
    void sendResetToken(String email, String resetToken, LocalDateTime expiresAt) throws MessagingException;
}
