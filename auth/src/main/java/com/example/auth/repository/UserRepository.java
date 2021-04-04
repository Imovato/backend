package com.example.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

import com.example.auth.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
  boolean existsByUsername(String username);
	
	User findByUsername(String username);

	@Transactional
	void deleteByUsername(String username);
}
