package com.unipampa.crud.validations.impl;

import com.unipampa.crud.dto.UserDTO;
import com.unipampa.crud.enums.UserType;
import com.unipampa.crud.exceptions.ValidateRegisterException;
import com.unipampa.crud.validations.ValidationsSignup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ValidateRoleAdministrator implements ValidationsSignup {

    private static final String USER_ADMINISTRATOR_NOT_ALLOWED_LOG = "Tentativa de criar usuário com role ADMINISTRATOR bloqueada. Username: {}";
    private static final String USERS_ADMINISTRATOR_NOT_ALLOWED = "Não é permitido criar usuários com perfil ADMINISTRATOR.";

    @Override
    public void validate(UserDTO userDTO) {
        if (userDTO.type() == UserType.ROLE_ADMINISTRATOR) {
            log.warn(USER_ADMINISTRATOR_NOT_ALLOWED_LOG, userDTO.userName());
            throw new ValidateRegisterException(USERS_ADMINISTRATOR_NOT_ALLOWED);
        }
    }
}
