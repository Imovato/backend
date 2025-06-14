package com.unipampa.crud.service;

import java.util.List;
import java.util.Optional;

import com.unipampa.crud.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
	void save(User user);

	List<User> findAll();

	void delete(String id);

	Optional<User> findByEmail(String email);

	Optional<User> findById(String id);

	boolean existsByUserName(String name);

	boolean existsByEmail(String email);

	boolean existsByCpf(String cpf);

	Page<User> findAll(Pageable pageable);

	void isOwnerOrAdmin(Optional<User> user);

}
