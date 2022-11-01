package com.example.payment.interfaces.service;

import com.example.payment.model.Property;
import com.example.payment.model.User;

public interface IUserService {

    User findUserById(Long id);
    void validarUsuario(User user, Property property);

}
