package com.unipampa.crud.service.impl;

import com.unipampa.crud.config.security.SecurityUtil;
import com.unipampa.crud.entities.User;
import com.unipampa.crud.exceptions.ResourceNotFoundException;
import com.unipampa.crud.repository.UserRepository;
import com.unipampa.crud.sender.UserSender;
import com.unipampa.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	private static final String USER_NOT_AUTHORIZED = "Você não tem permissão para acessar esse recurso.";
	private static final String USER_NOT_FOUND = "Usuário não encontrado.";
	private UserRepository userRepository;
	private UserSender userSender;

	@Autowired
	public UserServiceImpl(UserRepository repository, UserSender userSend) {
		this.userRepository = repository;
		this.userSender = userSend;
	}

	@Override
	@Transactional
	public void save(User user) {
		User userSaved = userRepository.save(user);
		userSender.sendMessage(userSaved);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public void delete(String id) {
		userRepository.deleteById(id);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}

	@Override
	public Optional<User> findById(String id) {
		var user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new ResourceNotFoundException(USER_NOT_FOUND);
		}
		return user;
	}

	@Override
	public boolean existsByUserName(String username) {
		return userRepository.existsByUserName(username);
	}

	@Override
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public boolean existsByCpf(String cpf) {
		return userRepository.existsByCpf(cpf);
	}

	@Override
	public Page<User> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	public void isOwnerOrAdmin(Optional<User> user) {
		if (!SecurityUtil.isOwnerOrAdmin(user.get().getId())) {
			throw new AccessDeniedException(USER_NOT_AUTHORIZED);
		}
	}




}
