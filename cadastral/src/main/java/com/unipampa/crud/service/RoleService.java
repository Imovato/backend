package com.unipampa.crud.service;

import com.unipampa.crud.entities.Role;

import java.util.Optional;

public interface RoleService {

    Optional<Role> findByName(String name);


}
