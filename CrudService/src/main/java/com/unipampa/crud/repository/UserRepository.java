package com.unipampa.crud.repository;

import com.unipampa.crud.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findUserByEmail(String email);

    boolean existsByUserName(String username);

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);

}