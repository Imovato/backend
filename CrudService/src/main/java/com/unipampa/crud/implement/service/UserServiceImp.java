package com.unipampa.crud.implement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unipampa.crud.interfaces.service.IUserService;
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

}
