package com.unipampa.crud.service.impl;

import com.unipampa.crud.service.PasswordResetEmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PasswordResetEmailServiceImpl implements PasswordResetEmailService {

    private final JavaMailSender mailSender;

    @Value("${app.mail.from:no-reply@imovato.local}")
    private String fromAddress;

    public PasswordResetEmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendResetToken(String email, String resetToken, LocalDateTime expiresAt) throws MessagingException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromAddress);
        message.setTo(email);
        message.setSubject("Imovato - Recuperação de senha");
        message.setText(
                "Recebemos uma solicitação para redefinir sua senha.\n\n" +
                "Seu token de recuperação é: " + resetToken + "\n" +
                "Esse token expira em: " + expiresAt + " UTC\n\n" +
                "Se você não solicitou isso, pode ignorar este e-mail."
        );
        mailSender.send(message);
    }
}
