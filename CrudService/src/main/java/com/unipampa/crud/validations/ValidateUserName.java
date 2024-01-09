package com.unipampa.crud.validations;

import com.unipampa.crud.dto.UserDTO;
import com.unipampa.crud.exceptions.ValidateSignupException;
import com.unipampa.crud.service.UserService;
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
        if (userService.existsByUserName(userDto.getUserName())) {
            log.error("Username {} is already taken!", userDto.getUserName());
            throw new ValidateSignupException("Username is already taken!");
        }
    }
}
