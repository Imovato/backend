package com.example.acquisition.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.acquisition.interfaces.services.IUserService;
import com.example.acquisition.model.User;
import com.example.acquisition.repository.UserRepository;

@Service
public class UserServiceImp implements IUserService{
	
	private UserRepository userRepository;
	
	@Autowired
	public UserServiceImp(UserRepository repository) {
		this.userRepository = repository;
	}

	@Override
	public User findUserById(Long id) {
		return userRepository.findUserById(id);
	}
		
	
}
