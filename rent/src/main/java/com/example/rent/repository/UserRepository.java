package com.example.rent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.rent.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);
}
