package com.example.rent.service;

import com.example.rent.entities.User;

import java.util.Optional;
import java.util.UUID;

public interface UserService {

    Optional<User> findById(String id);

}
