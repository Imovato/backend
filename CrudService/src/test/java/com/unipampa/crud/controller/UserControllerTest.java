package com.unipampa.crud.controller;

import com.unipampa.crud.interfaces.service.IContactService;
import com.unipampa.crud.interfaces.service.IUserService;
import com.unipampa.crud.model.Contact;
import com.unipampa.crud.model.Customer;
import com.unipampa.crud.model.User;
import com.unipampa.util.*;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;
    @Mock
    private IUserService iUserService;

    @BeforeEach
    void setUp(){
        BDDMockito.when(iUserService.findAllUsers())
                .thenReturn(Arrays.asList(UserCreator.createValidUser()));

        BDDMockito.when(iUserService.findUserById(ArgumentMatchers.anyLong()))
                .thenReturn(UserCreator.createValidUser());

        BDDMockito.doNothing().when(iUserService).saveUser(ArgumentMatchers.any(User.class));

        BDDMockito.when(iUserService.updateCustomer(ArgumentMatchers.any())) //rever aqui
                .thenReturn((Customer) UserCreator.createValidUpdateUser());

        BDDMockito.doNothing().when(iUserService).deleteUser(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("ListAll returns list of users when successful")
    void listAll_ReturnsListOfUsers_WhenSuccessful() {
        String expectedName = UserCreator.createValidUser().getName();
        List<User> users = (List<User>) userController.getAllUsers().getBody();
        Assertions.assertThat(users)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(users.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("ListAll returns an empty list of users when an user is not found")
    void listAll_ReturnsEmptyListOfAnime_WhenAnimeIsNotFound() {
        BDDMockito.when(iUserService.findAllUsers())
                .thenReturn(Collections.emptyList());

        List<User> users = (List<User>) userController.getAllUsers().getBody();
        Assertions.assertThat(users)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("Find By Id return user when successful")
    void findById_ReturnUser_WhenSuccessful() {
        Long expectedId = UserCreator.createValidUser().getId();

        User user = (User) userController.getCustomerById(String.valueOf(1L)).getBody();

        Assertions.assertThat(user)
                .isNotNull();
        Assertions.assertThat(user.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("Save contact when successful")
    void save_Contact_WhenSuccessful() {

        Assertions.assertThatCode(() -> userController.saveCustomer(UserDTO1.createUserPostRequestBody()))
                .doesNotThrowAnyException();
        ResponseEntity<Void> entity = userController.saveCustomer(UserDTO1.createUserPostRequestBody());
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
}
