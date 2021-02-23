package com.unipampa.crud.implement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unipampa.crud.interfaces.service.IUserService;
import com.unipampa.crud.model.Customer;
import com.unipampa.crud.model.Employee;
import com.unipampa.crud.model.Owner;
import com.unipampa.crud.model.User;
import com.unipampa.crud.repository.UserRepository;


@Service
public class UserServiceImp implements IUserService {
	
	private UserRepository userRepository;
	
	@Autowired
	public UserServiceImp(UserRepository repository) {
		this.userRepository = repository;
	}

	@Override
	@Transactional
	public void saveUser(User user) {
		userRepository.save(user);
	}

	@Override
	public Employee findEmployeeById(Long id) {
		return userRepository.findEmployeeById(id);
	}

	@Override
	public Customer findCustomerById(Long id) {
		return userRepository.findCustomerById(id);
	}

	@Override
	public Owner findOwnerById(Long id) {
		return userRepository.findOwnerById(id);
	}

	@Override
	public User findUserById(Long id) {
		return userRepository.findUserById(id);
	}

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

}