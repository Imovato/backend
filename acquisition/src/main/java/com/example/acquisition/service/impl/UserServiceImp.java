package com.example.acquisition.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.example.acquisition.service.ValidaCpfServiceImp;
import com.example.acquisition.service.ValidaRendaServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.acquisition.model.Property;
import com.example.acquisition.model.User;
import com.example.acquisition.repository.UserRepository;
import com.example.acquisition.interfaces.services.IUserService;
import com.example.acquisition.interfaces.services.IValidacaoService;

@Service
public class UserServiceImp implements IUserService{
	
	private UserRepository userRepository;

	List<IValidacaoService> validacoes = new ArrayList<>();


	@Autowired
	public UserServiceImp(UserRepository repository) {
		this.userRepository = repository;
	}

	@Override
	public User findUserById(Long id) {
		return userRepository.findUserById(id);
	}

	public void validarUsuario(User user, Property property){
		validacoes.forEach(element->element.validar(user, property));
	}

}
