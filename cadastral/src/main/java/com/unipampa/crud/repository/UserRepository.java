package com.unipampa.crud.repository;

import com.unipampa.crud.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findUserByEmail(String email);

    boolean existsByUserName(String username);

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);

    @EntityGraph(attributePaths = {"roles"}, type = EntityGraph.EntityGraphType.FETCH)
    Optional<User> findByUserName(String username);

    @EntityGraph(attributePaths = {"roles"}, type = EntityGraph.EntityGraphType.FETCH)
    Optional<User> findById(String userId);
}