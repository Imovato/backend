package com.unipampa.crud.service.impl;

import com.unipampa.crud.entities.Role;
import com.unipampa.crud.repository.RoleRepository;
import com.unipampa.crud.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findRoleByRoleName(name);
    }
}
