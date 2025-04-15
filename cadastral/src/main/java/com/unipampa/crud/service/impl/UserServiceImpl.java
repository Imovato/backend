package com.unipampa.crud.service.impl;

import com.unipampa.crud.entities.User;
import com.unipampa.crud.repository.UserRepository;
import com.unipampa.crud.sender.UserSender;
import com.unipampa.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

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
		return userRepository.findById(id);
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

}
