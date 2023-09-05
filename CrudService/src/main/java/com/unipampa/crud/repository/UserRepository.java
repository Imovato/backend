package com.unipampa.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unipampa.crud.model.Guest;
import com.unipampa.crud.model.Host;
import com.unipampa.crud.model.Owner;
import com.unipampa.crud.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Host findEmployeeById(Long id);

	Guest findCustomerById(Long id);


	Boolean existsByEmail(String email);

	User findUserByEmail(String email);
}
