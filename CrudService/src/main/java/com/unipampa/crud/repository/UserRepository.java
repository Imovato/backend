package com.unipampa.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unipampa.crud.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
