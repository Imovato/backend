package com.unipampa.crud.config.security;

import com.unipampa.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    public static final String USER_NOT_FOUND = "Usuário não encontrado para o id de usuário: ";

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            var user = userRepository.findByUserName(username);
            var opt = user.orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND + username));
            return UserDatailsImpl.build(opt);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Erro de conectividade ou ao carregar os dados do usuário: " + username, e);
        }
    }

    public UserDetails loadUserByUserId(String userId) throws AuthenticationCredentialsNotFoundException {
            var user = userRepository.findById(userId);
            var userModel = user.orElseThrow(() -> new AuthenticationCredentialsNotFoundException(USER_NOT_FOUND + userId));
            return UserDatailsImpl.build(userModel);

    }
}
