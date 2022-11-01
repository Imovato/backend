package com.example.payment.service;

import com.example.payment.interfaces.service.IUserService;
import com.example.payment.model.Property;
import com.example.payment.model.User;
import com.example.payment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImp implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void validarUsuario(User user, Property property) {

    }
}
