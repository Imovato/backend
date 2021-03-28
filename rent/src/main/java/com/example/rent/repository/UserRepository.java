package com.example.rent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rent.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
