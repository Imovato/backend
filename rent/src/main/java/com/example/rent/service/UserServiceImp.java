package com.example.rent.service;

import com.example.rent.interfaces.services.IUserService;
import com.example.rent.model.User;
import com.example.rent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements IUserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User finUserById(Long id) {
        return userRepository.findUserById(id);
    }
}
