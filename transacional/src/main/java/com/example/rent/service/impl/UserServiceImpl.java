package com.example.rent.service.impl;

import com.example.rent.entities.User;
import com.example.rent.repository.UserRepository;
import com.example.rent.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public Optional<User> findById(Long id) {
      return userRepository.findById(id);
    }

}
