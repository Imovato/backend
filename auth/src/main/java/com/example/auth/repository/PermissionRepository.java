package com.example.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.auth.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long>{
	
	
	@Query("SELECT p FROM Permission p WHERE p.description =: description")
	Permission findByPermission(@Param("description") String description);
	
}
