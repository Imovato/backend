package com.unipampa.crud.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unipampa.crud.dto.UserDTO;
import com.unipampa.crud.entities.User;
import com.unipampa.crud.enums.UserType;
import com.unipampa.crud.service.UserService;
import com.unipampa.crud.validations.ValidationsSignup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserResourceTest {

    @Mock
    private UserService userService;

    @Mock
    private ObjectMapper mapper;
    @Mock
    private ValidationsSignup validation;

    @InjectMocks
    private UserResource userResource;

    private UserDTO userDto;
    private User user;

    private String email = "test@example.com";

    @BeforeEach
    void setUp() {

        userDto = new UserDTO(
                "cooper@example.com",
                "Cooper",
                "johnSnow",
                "123.456.789-00",
                "(11) 99999-9999",
                "123 Main St, Springfield",
                UserType.ADMINITSTRATOR
        );

        user = User.builder()
                .userName("Cooper")
                .email(email)
                .build();

        userResource.validations = List.of(validation);
        userResource.mapper = mapper;
    }

    @Test
    void testSaveUserSuccess() {
        when(mapper.convertValue(userDto, User.class)).thenReturn(user);
        doNothing().when(validation).validate(userDto);
        doNothing().when(userService).save(user);

        ResponseEntity<Object> response = userResource.saveUser(userDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(userService).save(user);
    }

    @Test
    void testSaveUserValidationFailure() {
        doThrow(new RuntimeException("Invalid user data")).when(validation).validate(userDto);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> userResource.saveUser(userDto));
        assertEquals("Invalid user data", thrown.getMessage());
        verify(userService, never()).save(any());
    }

    @Test
    void testGetAllUsersSuccess() {
        Pageable pageable = PageRequest.of(0, 3, Sort.by("id").ascending());
        User user1 = User.builder().build();
        User user2 = User.builder().build();
        List<User> userList = List.of(user1, user2);
        Page<User> userPage = new PageImpl<>(userList, pageable, userList.size());
        when(userService.findAll(pageable)).thenReturn(userPage);

        ResponseEntity<Page<User>> response = userResource.getAllUsers(pageable);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userPage, response.getBody());
        verify(userService, times(1)).findAll(pageable);
    }

    @Test
    void testGetAllUsersFailure() {
        Pageable pageable = PageRequest.of(0, 3, Sort.by("id").ascending());
        when(userService.findAll(pageable)).thenThrow(new RuntimeException("Database error"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userResource.getAllUsers(pageable);
        });
        assertEquals("Database error", exception.getMessage());
    }

    @Test
    void testGetUserByEmailSuccess() {
        when(userService.findByEmail(email)).thenReturn(Optional.of(user));

        ResponseEntity<Object> response = userResource.getUserByEmail(email);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Optional<User> responseBody = (Optional<User>) response.getBody();
        assertNotNull(responseBody);
        assertTrue(responseBody.isPresent());
        assertEquals(email, responseBody.get().getEmail());
    }

    @Test
    void testGetUserByEmailNotFound() {
        when(userService.findByEmail(email)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = userResource.getUserByEmail(email);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Usuário não encontrado para esse email!", response.getBody());
    }

    @Test
    void testGetUserByIdSuccess() {
        when(userService.findById("1")).thenReturn(Optional.of(user));

        ResponseEntity<Object> response = userResource.getUserById("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Object responseBody = response.getBody();
        assertNotNull(responseBody);
        assertTrue(responseBody instanceof Optional<?>);

        @SuppressWarnings("unchecked")
        Optional<User> returnedUser = (Optional<User>) responseBody;
        assertTrue(returnedUser.isPresent());
        assertEquals("test@example.com", returnedUser.get().getEmail());
    }

    @Test
    void testGetUserByIdNotFound() {
        when(userService.findById("2")).thenReturn(Optional.empty());

        ResponseEntity<Object> response = userResource.getUserById("2");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Usuário não encontrado", response.getBody());
    }

    @Test
    void testUpdateUserSuccess() {
        when(userService.findById("1")).thenReturn(Optional.of(user));

        ResponseEntity<Object> response = userResource.updateUser(userDto, "1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userService).save(user);
        assertEquals("Cooper", user.getName());
        assertEquals("cooper@example.com", user.getEmail());
    }

    @Test
    void testUpdateUserNotFound() {
        when(userService.findById("2")).thenReturn(Optional.empty());

        ResponseEntity<Object> response = userResource.updateUser(userDto, "2");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("usuário não encontrado para esse id, portanto não pode ser atualizado!", response.getBody());
        verify(userService, never()).save(any());
    }

    @Test
    void testDeleteUserSuccess() {
        String id = "1";
        when(userService.findById(id)).thenReturn(Optional.of(user));

        ResponseEntity<Object> response = userResource.deleteUser(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Usuário deletado!", response.getBody());
        verify(userService).delete(id);
    }

    @Test
    void testDeleteUserNotFound() {
        String id = "2";
        when(userService.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = userResource.deleteUser(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Usuário não encontrado para esse id, portanto não pode ser deletado!", response.getBody());
        verify(userService, never()).delete(any());
    }

}