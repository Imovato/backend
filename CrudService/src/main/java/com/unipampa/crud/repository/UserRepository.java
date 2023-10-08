package com.unipampa.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unipampa.crud.model.Guest;
import com.unipampa.crud.model.Host;
import com.unipampa.crud.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

	User findUserByEmail(String email);
}
