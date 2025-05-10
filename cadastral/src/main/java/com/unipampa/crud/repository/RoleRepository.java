package com.unipampa.crud.repository;

import com.unipampa.crud.entities.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface RoleRepository extends MongoRepository<Role, String> {

    Optional<Role> findRoleByRoleName(String name);

}
