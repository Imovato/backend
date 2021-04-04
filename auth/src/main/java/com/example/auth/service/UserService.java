package com.example.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.example.auth.exception.CustomHttpException;
import com.example.auth.repository.UserRepository;
import com.example.auth.security.JwtTokenProvider;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	public String signin(String username, String password) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
		} catch(AuthenticationException e) {
			throw new CustomHttpException("Combinação de usuário/senha inválida", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

}
