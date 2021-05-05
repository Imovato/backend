package com.unipampa.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unipampa.crud.model.Customer;
import com.unipampa.crud.model.Employee;
import com.unipampa.crud.model.Owner;
import com.unipampa.crud.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Employee findEmployeeById(Long id);

	Customer findCustomerById(Long id);

	Owner findOwnerById(Long id);

	User findUserById(Long id);

	Boolean existsByEmail(String email);

	Customer findCustomerByEmail(String email);
}
