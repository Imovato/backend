package com.unipampa.crud.validations;

import com.unipampa.crud.dto.UserDTO;
import com.unipampa.crud.enums.UserType;
import com.unipampa.crud.exceptions.ValidateRegisterException;
import com.unipampa.crud.service.UserService;
import com.unipampa.crud.validations.impl.ValidateUserName;
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
class ValidateUserNameTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private ValidateUserName validateUserName;

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
    void testValidateUserNameSuccess() {
        when(userService.existsByUserName("Cooper")).thenReturn(false);

        assertDoesNotThrow(() -> validateUserName.validate(userDto));

        verify(userService).existsByUserName("Cooper");
    }


    @Test
    void testValidateUserNameFailure() {
        // Cenário de falha: quando o nome de usuário já existe, o método deve lançar ValidateRegisterException.
        when(userService.existsByUserName("Cooper")).thenReturn(true);

        ValidateRegisterException exception = assertThrows(ValidateRegisterException.class, () -> {
            validateUserName.validate(userDto);
        });

        // Verifica se a mensagem da exceção é a esperada
        assertEquals("Username is already taken!", exception.getMessage());

        // Verifica se o método existsByUserName foi chamado com o valor correto
        verify(userService).existsByUserName("Cooper");
    }


}