package com.example.rent.interfaces.services;

import com.example.rent.model.User;

import java.util.UUID;

public interface IUserService {
    User findUserById(UUID id);
}
