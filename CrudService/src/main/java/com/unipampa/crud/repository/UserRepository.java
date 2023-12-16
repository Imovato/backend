package com.unipampa.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unipampa.crud.model.Guest;
import com.unipampa.crud.model.Host;
import com.unipampa.crud.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

	Optional<User> findUserByEmail(String email);

    boolean existsByUserName(String username);

    boolean existsByEmail(String email);
}
