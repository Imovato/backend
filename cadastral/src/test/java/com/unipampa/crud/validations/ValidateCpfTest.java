package com.unipampa.crud.validations;

import com.unipampa.crud.dto.UserDTO;
import com.unipampa.crud.enums.UserType;
import com.unipampa.crud.exceptions.ValidateRegisterException;
import com.unipampa.crud.service.UserService;
import com.unipampa.crud.validations.impl.ValidateCpf;
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
class ValidateCpfTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private ValidateCpf validateCpf;

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
    void shouldValidateCpfSuccess() {
        when(userService.existsByCpf(userDto.cpf())).thenReturn(false);

        assertDoesNotThrow(() -> validateCpf.validate(userDto));

        verify(userService).existsByCpf(userDto.cpf());
    }

    @Test
    void shouldValidateCpfFailure() {
        when(userService.existsByCpf(userDto.cpf())).thenReturn(true);

        ValidateRegisterException exception = assertThrows(ValidateRegisterException.class, () -> {
            validateCpf.validate(userDto);
        });

        assertEquals("CPF is already registered!", exception.getMessage());

        verify(userService).existsByCpf(userDto.cpf());
    }

}