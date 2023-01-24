package com.example.rent.repository;

import com.example.rent.model.Rent;
import com.example.rent.util.RentCreator;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for Rent Repository")
@Log4j2
class RentRepositoryTest {

    @Autowired
    private RentRepository rentRepository;

    @Test
    @DisplayName("Save persists rent when Successful")
    void save_PersistRent_WhenSuccessful() {
        Rent rentToBeSaved = RentCreator.createRentToSaved();
        Rent rentSaved = this.rentRepository.save(rentToBeSaved);
        Assertions.assertThat(rentSaved).isNotNull();
        Assertions.assertThat(rentSaved.getId()).isNotNull();
        Assertions.assertThat(rentSaved.getValue()).isEqualTo(rentToBeSaved.getValue());
    }

    @Test
    @DisplayName("Save updates rent when Successful")
    void save_UpdatesRent_WhenSuccessful() {
        Rent rentToBeSaved = RentCreator.createRentToSaved();
        Rent rentSaved = this.rentRepository.save(rentToBeSaved);
        rentSaved.setValue(1498D);
        Rent rentUpdated = this.rentRepository.save(rentSaved);

        Assertions.assertThat(rentUpdated).isNotNull();
        Assertions.assertThat(rentUpdated.getId()).isNotNull();
        Assertions.assertThat(rentUpdated.getValue()).isEqualTo(rentSaved.getValue());
    }

    @Test
    @DisplayName("Delete removes rent when Successful")
    void delete_RemovesRent_WhenSuccessful() {
        Rent rentToBeSaved = RentCreator.createRentToSaved();
        Rent rentSaved = this.rentRepository.save(rentToBeSaved);

        this.rentRepository.delete(rentSaved);

        Optional<Rent> rentOptional = this.rentRepository.findById(rentSaved.getId());
        Assertions.assertThat(rentOptional).isEmpty();
    }

    @Test
    @DisplayName("Find by rent when Successful")
    void find_ByRent_WhenSuccessful() {
        Rent rentToBeSaved = RentCreator.createRentToSaved();
        Rent rentSaved = this.rentRepository.save(rentToBeSaved);
        Rent rentFind = this.rentRepository.findById(rentSaved.getId()).get();

        Assertions.assertThat(rentFind).isNotNull();
        Assertions.assertThat(rentFind.getId()).isNotNull();
    }

    @Test
    @DisplayName("Find by id customer returns list of rent when Successful")
    void findById_ReturnsListOfRent_WhenSuccessful() {
        Rent rentToBeSaved = RentCreator.createRentToSaved();
        Rent rentSaved = this.rentRepository.save(rentToBeSaved);
        List<Rent> rents = this.rentRepository.findByCustomerId(rentSaved.getCustomer().getId());

        Assertions.assertThat(rents)
                .isNotEmpty()
                .contains(rentSaved);
    }

    @Test
    @DisplayName("Find by id customer returns empty list when no rent is found")
    void findByIdCustomer_ReturnsEmptyList_WhenRentIsNotFound() {
        List<Rent> rents = this.rentRepository.findByCustomerId(50L);

        Assertions.assertThat(rents).isEmpty();
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when value is empty")
    void save_ThrowsConstraintViolationException_WhenValueIsEmpty() {
        Rent rent = new Rent();
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(()->this.rentRepository.save(rent))
                .withMessageContaining("The value of rent cannot be empty");
    }
}