package com.unipampa.crud.service;

import java.util.List;
import java.util.Optional;

import com.unipampa.crud.model.Guest;
import com.unipampa.crud.model.Host;
import com.unipampa.crud.model.Owner;
import com.unipampa.crud.model.User;

public interface IUserService {
	void saveUser(User user);

	Host findEmployeeById(Long id);

	Guest findCustomerById(Long id);

	List<User> findAllUsers();

	void deleteUser(Long id);

	Host updateEmployee(Host employee);

	Guest updateCustomer(Guest customer);

	Owner updateOwner(Owner owner);

	Boolean existsByEmail(String email);

	Optional<User> findUserByEmail(String email);

	Optional<User> findById(Long id);
}
