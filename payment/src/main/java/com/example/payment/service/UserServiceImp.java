package com.example.payment.service;

import java.util.List;

import com.example.payment.interfaces.service.IUserService;
import com.example.payment.interfaces.service.IValidacaoService;
import com.example.payment.model.Property;
import com.example.payment.model.User;
import com.example.payment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImp implements IUserService {

    private UserRepository userRepository;
    List<IValidacaoService> validacoes;

    @Autowired
    public UserServiceImp(UserRepository repository) {
        this.userRepository = repository;
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public void validarUsuario(User user, Property property){
        validacoes.forEach(element->element.validar(user, property));
    }

}