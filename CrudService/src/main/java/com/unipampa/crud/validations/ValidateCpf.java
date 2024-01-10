package com.unipampa.crud.validations;

import com.unipampa.crud.dto.UserDTO;
import com.unipampa.crud.exceptions.ValidateSignupException;
import com.unipampa.crud.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ValidateCpf implements ValidationsSignup{

    @Autowired
    private UserService userService;
    @Override
    public void validate(UserDTO userDto) {
        if(userService.existsByCpf(userDto.getCpf())){
            log.error("CPF {} is already registered!", userDto.getCpf());
            throw new ValidateSignupException("CPF is already registered!");
        }
    }
}
