package com.example.rent.service.interfaces;

import com.example.rent.entities.User;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    User save(User user);

    Optional<User> findById(Long id);

//    void validateCustomer(Customer customer, Accommodation property);
}
