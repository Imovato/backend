package com.unipampa.crud.service;

import java.util.List;
import java.util.Optional;

import com.unipampa.crud.model.User;

public interface UserService {
	void save(User user);

	List<User> findAll();

	void delete(String id);

	Optional<User> findByEmail(String email);

	Optional<User> findById(String id);
}
