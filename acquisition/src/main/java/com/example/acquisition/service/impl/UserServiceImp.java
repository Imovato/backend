package com.example.acquisition.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.acquisition.exceptions.ValidacaoException;
import com.example.acquisition.service.ValidaCpfServiceImp;
import com.example.acquisition.service.ValidaRendaServiceImp;
import com.example.acquisition.service.ValidaStatusServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import com.example.acquisition.model.Property;
import com.example.acquisition.model.User;
import com.example.acquisition.repository.UserRepository;
import com.example.acquisition.interfaces.services.IUserService;
import com.example.acquisition.interfaces.services.IValidacaoService;

@Service
public class UserServiceImp implements IUserService{
    List<IValidacaoService> validacoes = Arrays.asList(
            new ValidaCpfServiceImp(),
            new ValidaRendaServiceImp(),
			new ValidaStatusServiceImp()
    );
    @Autowired
	private UserRepository userRepository;

	@Autowired
	public UserServiceImp(UserRepository repository) {
		this.userRepository = repository;
	}

	@Override
	public User findUserById(Long id) {
		return userRepository.findUserById(id);
	}

	public void validateUser(User user, Property property) throws ValidacaoException {
		validacoes.forEach(element->element.validate(user, property));
	}

}
