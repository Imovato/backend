package com.unipampa.crud.service;

import java.util.List;

import com.unipampa.crud.model.Guest;
import com.unipampa.crud.model.Host;
import com.unipampa.crud.model.Owner;
import com.unipampa.crud.model.User;

public interface IUserService {
	void saveUser(User user);

	Host findEmployeeById(Long id);

	Guest findCustomerById(Long id);

	Owner findOwnerById(Long id);

	User findUserById(Long id);

	List<User> findAllUsers();

	void deleteUser(Long id);

	Host updateEmployee(Host employee);

	Guest updateCustomer(Guest customer);

	Owner updateOwner(Owner owner);

	Boolean existsByEmail(String email);

	Guest findCustomerByEmail(String email);
}
