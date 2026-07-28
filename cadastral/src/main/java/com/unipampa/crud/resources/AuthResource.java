package com.unipampa.crud.resources;

import com.unipampa.crud.config.security.JwtProvider;
import com.unipampa.crud.dto.ForgotPasswordDTO;
import com.unipampa.crud.dto.JwtDTO;
import com.unipampa.crud.dto.LoginDTO;
import com.unipampa.crud.dto.PasswordResetResponseDTO;
import com.unipampa.crud.dto.ResetPasswordDTO;
import com.unipampa.crud.entities.User;
import com.unipampa.crud.service.PasswordResetEmailService;
import com.unipampa.crud.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("/auth")
public class AuthResource {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordResetEmailService passwordResetEmailService;

    private static final int RESET_TOKEN_EXPIRATION_MINUTES = 30;

    @PostMapping()
    public ResponseEntity<JwtDTO> authenticateUser(@Valid @RequestBody LoginDTO loginDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.password()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtProvider.generateJwtToken(authentication);
            return ResponseEntity.ok(new JwtDTO(jwt));

        } catch (BadCredentialsException e) {
            log.error("Credenciais inválidas para: {}", loginDTO.email());
            return ResponseEntity.status(401).body(null);
        } catch (Exception e) {
            System.out.println("🚨 Falha na autenticação: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            return ResponseEntity.status(401).body(null);
        }

    }

    @PostMapping("/forgot-password")
    public ResponseEntity<PasswordResetResponseDTO> requestPasswordReset(
            @Valid @RequestBody ForgotPasswordDTO forgotPasswordDTO) {
        Optional<User> userOptional = userService.findByEmail(forgotPasswordDTO.email());
        if (userOptional.isEmpty()) {
            return ResponseEntity.ok(new PasswordResetResponseDTO(
                    "Se o e-mail existir na base, um token de recuperação será gerado.",
                    null,
                    null));
        }

        User user = userOptional.get();
        String resetToken = UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();
        LocalDateTime expiresAt = LocalDateTime.now(ZoneId.of("UTC"))
                .plusMinutes(RESET_TOKEN_EXPIRATION_MINUTES);

        user.setPasswordResetToken(passwordEncoder.encode(resetToken));
        user.setPasswordResetTokenExpiresAt(expiresAt);
        userService.save(user);

        try {
            passwordResetEmailService.sendResetToken(forgotPasswordDTO.email(), resetToken, expiresAt);
            log.info("Token de reset enviado para {} com expiração em {}", forgotPasswordDTO.email(), expiresAt);
        } catch (MessagingException e) {
            log.warn("Falha ao enviar e-mail de recuperação para {}: {}", forgotPasswordDTO.email(), e.getMessage());
        }

        return ResponseEntity.ok(new PasswordResetResponseDTO(
                "Token de recuperação gerado com sucesso.",
                resetToken,
                expiresAt.toString()));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@Valid @RequestBody ResetPasswordDTO resetPasswordDTO) {
        Optional<User> userOptional = userService.findByEmail(resetPasswordDTO.email());
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("E-mail não encontrado.");
        }

        User user = userOptional.get();
        if (user.getPasswordResetToken() == null || user.getPasswordResetTokenExpiresAt() == null) {
            return ResponseEntity.badRequest().body("Nenhum token de recuperação foi solicitado.");
        }

        if (user.getPasswordResetTokenExpiresAt().isBefore(LocalDateTime.now(ZoneId.of("UTC")))) {
            user.setPasswordResetToken(null);
            user.setPasswordResetTokenExpiresAt(null);
            userService.save(user);
            return ResponseEntity.badRequest().body("Token de recuperação expirado.");
        }

        if (!passwordEncoder.matches(resetPasswordDTO.resetToken(), user.getPasswordResetToken())) {
            return ResponseEntity.badRequest().body("Token de recuperação inválido.");
        }

        user.setPassword(passwordEncoder.encode(resetPasswordDTO.newPassword()));
        user.setPasswordResetToken(null);
        user.setPasswordResetTokenExpiresAt(null);
        user.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        userService.save(user);

        return ResponseEntity.ok("Senha atualizada com sucesso.");
    }

}
