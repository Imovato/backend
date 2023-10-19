package com.unipampa.crud.validations;

import com.unipampa.crud.dto.UserDTO;
import com.unipampa.crud.exceptions.ValidateSignupException;
import com.unipampa.crud.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ValidateEmail implements ValidationsSignup {

    @Autowired
    private UserService userService;

    @Override
    public void validate(UserDTO userDto) {
        if (userService.existsByEmail(userDto.getEmail())) {
            log.warn("Email {} is already taken!", userDto.getEmail());
            throw new ValidateSignupException("Email is already taken!");
        }
    }
}
