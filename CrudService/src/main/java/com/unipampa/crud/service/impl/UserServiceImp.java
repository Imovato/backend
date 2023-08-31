package com.unipampa.crud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unipampa.crud.service.IUserService;
import com.unipampa.crud.model.Guest;
import com.unipampa.crud.model.Host;
import com.unipampa.crud.model.Owner;
import com.unipampa.crud.model.User;
import com.unipampa.crud.repository.UserRepository;
import com.unipampa.crud.sender.UserSender;

@Service
public class UserServiceImp implements IUserService {

	private UserRepository userRepository;
	private UserSender userSender;

	@Autowired
	public UserServiceImp(UserRepository repository, UserSender userSend) {
		this.userRepository = repository;
		this.userSender = userSend;
	}

	@Override
	@Transactional
	public void saveUser(User user) {
		User userSaved = userRepository.save(user);
		userSender.sendMessage(userSaved);
	}

	@Override
	public Host findEmployeeById(Long id) {
		return userRepository.findEmployeeById(id);
	}

	@Override
	public Guest findCustomerById(Long id) {
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

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public Host updateEmployee(Host employee) {
		return userRepository.save(employee);
	}

	@Override
	public Guest updateCustomer(Guest customer) {
		return userRepository.save(customer);
	}

	@Override
	public Owner updateOwner(Owner owner) {
		return userRepository.save(owner);
	}

	@Override
	public Boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public Guest findCustomerByEmail(String email) {
		return userRepository.findCustomerByEmail(email);
	}

}
