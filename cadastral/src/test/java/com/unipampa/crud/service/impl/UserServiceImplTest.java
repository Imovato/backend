package com.unipampa.crud.service.impl;

import com.unipampa.crud.entities.User;
import com.unipampa.crud.repository.UserRepository;
import com.unipampa.crud.sender.UserSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserSender userSender;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    private Pageable pageable;

    private Page<User> userPage;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id("12345")
                .userName("Cooper")
                .name("Cooper")
                .email("teste@email.com")
                .cpf("123.456.789-00")
                .build();
    }

    @Test
    void testSaveUserSuccess() {
        User savedUser = User.builder().id("12345").build();
        when(userRepository.save(user)).thenReturn(savedUser);

        userService.save(user);

        verify(userRepository, times(1)).save(user);
        verify(userSender, times(1)).sendMessage(savedUser);
    }

    @Test
    void testSaveUserFailure() {
        when(userRepository.save(user)).thenThrow(new RuntimeException("Database error"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.save(user);
        });

        assertEquals("Database error", exception.getMessage());
        verify(userSender, never()).sendMessage(any());
    }

    @Test
    void testFindAllEmpty() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        List<User> result = userService.findAll();

        assertNotNull(result, "A lista retornada não deve ser nula.");
        assertTrue(result.isEmpty(), "A lista retornada deve estar vazia.");
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testFindAllNonEmpty() {
        User user1 = User.builder()
                .id("12345")
                .userName("Cooper")
                .build();

        User user2 = User.builder()
                .id("98765")
                .userName("Murphy")
                .build();

        List<User> users = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.findAll();

        assertNotNull(result, "A lista retornada não deve ser nula.");
        assertEquals(2, result.size(), "A lista retornada deve conter 2 usuários.");
        assertEquals("12345", result.get(0).getId());
        assertEquals("98765", result.get(1).getId());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testDeleteSuccess() {
        String id = user.getId();
        doNothing().when(userRepository).deleteById(id);

        userService.delete(id);

        verify(userRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeleteThrowsException() {
        String id = user.getId();
        doThrow(new RuntimeException("Deletion failed")).when(userRepository).deleteById(id);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.delete(id));

        assertEquals("Deletion failed", exception.getMessage());
        verify(userRepository, times(1)).deleteById(id);
    }


    @Test
    void testFindByEmailSuccess() {
        when(userRepository.findUserByEmail("teste@email.com"))
                .thenReturn(Optional.of(user));

        Optional<User> result = userService.findByEmail("teste@email.com");

        assertTrue(result.isPresent());
        assertEquals("teste@email.com", result.get().getEmail());
        assertEquals("Cooper", result.get().getName());
        verify(userRepository, times(1)).findUserByEmail("teste@email.com");
    }

    @Test
    void testFindByEmailNotFound() {
        when(userRepository.findUserByEmail("naoexiste@email.com"))
                .thenReturn(Optional.empty());

        Optional<User> result = userService.findByEmail("naoexiste@email.com");

        assertFalse(result.isPresent());
        verify(userRepository, times(1)).findUserByEmail("naoexiste@email.com");
    }

    @Test
    void testFindByIdSuccess() {
        String id = "12345";
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        Optional<User> result = userService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
        assertEquals("teste@email.com", result.get().getEmail());
        assertEquals("Cooper", result.get().getName());
        verify(userRepository, times(1)).findById(id);
    }


    @Test
    void testFindByIdNotFound() {
        String id = "999";
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        Optional<User> result = userService.findById(id);

        assertFalse(result.isPresent());
        verify(userRepository, times(1)).findById(id);
    }

    @Test
    void testExistsByUserNameUserExists() {
        String username = user.getUserName();
        when(userRepository.existsByUserName(username)).thenReturn(true);

        boolean result = userService.existsByUserName(username);

        assertTrue(result);
        verify(userRepository, times(1)).existsByUserName(username);
    }

    @Test
    void testExistsByUserNameUserDoesNotExist() {
        String userNotExists = "nonexistentUser";
        when(userRepository.existsByUserName(userNotExists)).thenReturn(false);

        boolean result = userService.existsByUserName(userNotExists);

        assertFalse(result);
        verify(userRepository, times(1)).existsByUserName(userNotExists);
    }

    @Test
    void testExistsByEmailEmailExists() {
        String email = user.getEmail();
        when(userRepository.existsByEmail(email)).thenReturn(true);

        boolean result = userService.existsByEmail(email);

        assertTrue(result);
        verify(userRepository, times(1)).existsByEmail(email);
    }

    @Test
    void testExistsByEmailEmailDoesNotExist() {
        String emailNotExists = "naoexiste@email.com";
        when(userRepository.existsByEmail(emailNotExists)).thenReturn(false);

        boolean result = userService.existsByEmail(emailNotExists);

        assertFalse(result);
        verify(userRepository, times(1)).existsByEmail(emailNotExists);
    }

    @Test
    void testExistsByCpfWithValidCpf() {
        String cpf = user.getCpf();
        when(userRepository.existsByCpf(cpf)).thenReturn(true);

        boolean result = userService.existsByCpf(cpf);

        assertTrue(result);
        verify(userRepository, times(1)).existsByCpf(cpf);
    }

    @Test
    void testExistsByCpfWithInvalidCpf() {
       String cpfNotExists = "000.000.000-00";
        when(userRepository.existsByCpf(cpfNotExists)).thenReturn(false);

        boolean result = userService.existsByCpf(cpfNotExists);

        assertFalse(result);
        verify(userRepository, times(1)).existsByCpf(cpfNotExists);
    }

    @Test
    void testFindAllSuccess() {
        User user1 = User.builder()
                .name("Cooper")
                .build();

        User user2 = User.builder()
                .name("Murphy")
                .build();

        pageable = PageRequest.of(0, 10, Sort.by("name"));
        userPage = new PageImpl<>(List.of(user1, user2), pageable, 2);
        when(userRepository.findAll(pageable)).thenReturn(userPage);

        Page<User> result = userService.findAll(pageable);

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        assertEquals(2, result.getContent().size());
        assertEquals("Cooper", result.getContent().get(0).getName());
        assertEquals("Murphy", result.getContent().get(1).getName());
        verify(userRepository, times(1)).findAll(pageable);
    }


    @Test
    void testFindAllEmptyPage() {
        pageable = PageRequest.of(0, 1, Sort.by("name"));
        when(userRepository.findAll(pageable)).thenReturn(Page.empty(pageable));

        Page<User> result = userService.findAll(pageable);

        assertNotNull(result);
        assertEquals(0, result.getTotalElements());
        assertTrue(result.getContent().isEmpty());
        verify(userRepository, times(1)).findAll(pageable);
    }

}