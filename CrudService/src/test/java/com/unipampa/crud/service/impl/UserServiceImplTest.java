package com.unipampa.crud.service.impl;

import com.unipampa.crud.enums.UserType;
import com.unipampa.crud.model.Guest;
import com.unipampa.crud.model.Host;
import com.unipampa.crud.model.User;
import com.unipampa.crud.repository.UserRepository;
import com.unipampa.crud.sender.UserSender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserSender userSender;

    @Mock
    private User user;

    @Test
    void shouldSaveUserTypeHost() {
        Host user = Host.builder()
                .id("123")
                .cpf("8387738287348")
                .email("samuelmodesto@email.com")
                .name("Samuel Modesto")
                .type(UserType.HOST)
                .build();

        service.save(user);
        when(userRepository.save(user)).thenReturn(user);
        Host userReturn = userRepository.save(user);

        assertEquals(UserType.HOST, userReturn.getType());
    }

    @Test
    void shouldSaveUserTypeGuest() {
        Guest user = Guest.builder()
                .id("123")
                .cpf("8387738287348")
                .email("samuelmodesto@email.com")
                .name("Samuel Modesto")
                .type(UserType.GUEST)
                .build();

        service.save(user);
        when(userRepository.save(user)).thenReturn(user);
        Guest userReturn = userRepository.save(user);

        assertEquals(UserType.GUEST, userReturn.getType());
    }

    @Test
    void shouldFindAllUsers() {
        List<User> users = service.findAll();
        then(userRepository).should().findAll();
        assertNotNull(users);
    }

    @Test
    void shouldDeleteUserById() {
        service.delete("123");
        then(userRepository).should().deleteById("123");
    }

    @Test
    void shouldFindUserByEmail() {
        String email = "samuelmodesto@email.com";
        when(service.findByEmail(email)).thenReturn(Optional.of(user));
        Optional<User> userReturn = service.findByEmail(email);
        assertNotNull(userReturn);
    }

    @Test
    void shouldFindUserById() {
        String id = "123";
        when(service.findById(id)).thenReturn(Optional.of(user));
        Optional<User> userReturn = service.findById(id);
        assertNotNull(userReturn);
    }

}