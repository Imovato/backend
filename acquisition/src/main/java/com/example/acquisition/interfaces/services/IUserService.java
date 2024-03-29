package com.example.acquisition.interfaces.services;

import com.example.acquisition.model.Property;
import com.example.acquisition.model.User;

public interface IUserService{
	
	User findUserById(Long id);

	void validateUser(User user, Property property);
}
