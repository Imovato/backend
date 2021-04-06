package com.example.acquisition.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.acquisition.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);
}
