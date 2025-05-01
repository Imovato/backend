package com.unipampa.crud.service.impl;

import com.unipampa.crud.repository.RoleRepository;
import com.unipampa.crud.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;
}
