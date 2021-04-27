package com.example.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

import com.example.auth.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	boolean existsByEmail(String email);
	
	User findByEmail(String username);

	@Transactional
	void deleteByEmail(String username);
}
