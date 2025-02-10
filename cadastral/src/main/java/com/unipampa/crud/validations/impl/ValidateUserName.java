package com.unipampa.crud.validations.impl;

import com.unipampa.crud.dto.UserDTO;
import com.unipampa.crud.exceptions.ValidateRegisterException;
import com.unipampa.crud.service.UserService;
import com.unipampa.crud.validations.ValidationsSignup;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class ValidateUserName implements ValidationsSignup {

    @Autowired
    private UserService userService;

    @Override
    public void validate(UserDTO userDto) {
        if (userService.existsByUserName(userDto.userName())) {
            log.error("Username {} is already taken!", userDto.userName());
            throw new ValidateRegisterException("Username is already taken!");
        }
    }
}
