package com.unipampa.crud.validations;

import com.unipampa.crud.dto.UserDTO;
import com.unipampa.crud.enums.UserType;
import com.unipampa.crud.exceptions.ValidateRegisterException;
import com.unipampa.crud.service.UserService;
import com.unipampa.crud.validations.impl.ValidateEmail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateEmailTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private ValidateEmail validateEmail;

    private UserDTO userDto;

    @BeforeEach
    void setUp() {
        userDto = new UserDTO(
                "cooper@example.com",
                "Cooper",
                "Cooper",
                "123.456.789-00",
                "123456",
                "ADMIN",
                UserType.ROLE_ADMINISTRATOR,
                "(11) 99999-9999",
                "123 Main St, Springfield"
        );
    }

    @Test
    void testValidateEmailSuccess() {
        when(userService.existsByEmail(userDto.email())).thenReturn(false);

        assertDoesNotThrow(() -> validateEmail.validate(userDto));

        verify(userService).existsByEmail(userDto.email());
    }

    @Test
    void testValidateEmailFailure() {
        when(userService.existsByEmail(userDto.email())).thenReturn(true);

        ValidateRegisterException exception = assertThrows(ValidateRegisterException.class, () -> {
            validateEmail.validate(userDto);
        });

        assertEquals("Email is already registered!", exception.getMessage());
        verify(userService).existsByEmail(userDto.email());
    }

}