package com.unipampa.crud.repository;

import com.unipampa.crud.entities.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
}
