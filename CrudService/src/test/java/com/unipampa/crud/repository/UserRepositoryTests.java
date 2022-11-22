package com.unipampa.crud.repository;

import com.unipampa.crud.model.Contact;
import com.unipampa.crud.model.User;
import com.unipampa.util.ContactCreator;
import com.unipampa.util.UserCreator;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for User Repository")
@Log4j2
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Save creates user when Successful")
    void save_PersistUser_WhenSuccessful(){
        User userToBeSaved = UserCreator.createUserToSaved();
        User userSaved = this.userRepository.save(userToBeSaved);
        Assertions.assertThat(userSaved).isNotNull();
        Assertions.assertThat(userSaved.getId()).isNotNull();
        Assertions.assertThat(userSaved.getName()).isEqualTo(userToBeSaved.getName());
    }

    @Test
    @DisplayName("Save updates user when Successful")
    void save_UpdateUser_WhenSuccessful(){
        User userToBeSaved = UserCreator.createUserToSaved();
        User userSaved = this.userRepository.save(userToBeSaved);

        User userUpdated = this.userRepository.save(userSaved);
        Assertions.assertThat(userSaved).isNotNull();
        Assertions.assertThat(userSaved.getId()).isNotNull();
        Assertions.assertThat(userUpdated.getName()).isEqualTo(userToBeSaved.getName());
    }

    @Test
    @DisplayName("Deletes removes user when Successful")
    void delete_RemovesUser_WhenSuccessful(){
        User userToBeSaved = UserCreator.createUserToSaved();
        User userSaved = this.userRepository.save(userToBeSaved);

        this.userRepository.delete(userSaved);
        Optional<User> userOptional = this.userRepository.findById(userSaved.getId());
        Assertions.assertThat(userOptional).isEmpty();

    }

    @Test
    @DisplayName("Find by user when Successful")
    void find_ByUser_WhenSuccessful(){
        User userToBeSaved = UserCreator.createUserToSaved();
        User userSaved = this.userRepository.save(userToBeSaved);
        User userFind = this.userRepository.findUserById(userSaved.getId());

        Assertions.assertThat(userFind).isNotNull();
        Assertions.assertThat(userFind.getId()).isNotNull();

    }

    @Test
    @DisplayName("Find by user return null when no user not found")
    void findByUser_ReturnsNull_WhenUserIsNotFound() {
        User userToBeSaved = UserCreator.createUserToSaved();
        User userFind = this.userRepository.findUserById(userToBeSaved.getId());

        Assertions.assertThat(userFind).isNull();
    }
}
