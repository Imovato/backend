package com.unipampa.crud.validations.impl;

import com.unipampa.crud.dto.UserDTO;
import com.unipampa.crud.exceptions.ValidateRegisterException;
import com.unipampa.crud.service.UserService;
import com.unipampa.crud.validations.ValidationsSignup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ValidateCpf implements ValidationsSignup {

    @Autowired
    private UserService userService;
    @Override
    public void validate(UserDTO userDto) {
        if(userService.existsByCpf(userDto.cpf())){
            log.error("CPF {} is already registered!", userDto.cpf());
            throw new ValidateRegisterException("CPF is already registered!");
        }
    }
}
