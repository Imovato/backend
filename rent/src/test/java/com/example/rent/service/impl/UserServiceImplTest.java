package com.example.rent.service.impl;

import com.example.rent.entities.User;
import com.example.rent.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User mockUser;

    @BeforeEach
    void setUp() {

        mockUser = new User();
        mockUser.setId(1L);
        mockUser.setName("Cooper");
        mockUser.setEmail("cooper@email.com");
    }

    @Test
    void testFindByIdSuccess() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        Optional<User> result = userService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        assertEquals("Cooper", result.get().getName());
        assertEquals("cooper@email.com", result.get().getEmail());
        verify(userRepository, times(1)).findById(1L);
    }


    @Test
    void testFindByIdNotFound() {
        when(userRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<User> result = userService.findById(2L);

        assertFalse(result.isPresent());
        verify(userRepository, times(1)).findById(2L);
    }

}