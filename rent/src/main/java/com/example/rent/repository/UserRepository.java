package com.example.rent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.rent.model.User;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(UUID id);
}
