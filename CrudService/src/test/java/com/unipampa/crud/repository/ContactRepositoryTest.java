package com.unipampa.crud.repository;

import com.unipampa.crud.model.Contact;
import com.unipampa.util.ContactCreator;
import lombok.extern.log4j.Log4j2;
import lombok.var;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Tests for Contact Repository")
@Log4j2
class ContactRepositoryTest {
    @Autowired
    private ContactRepository contactRepository;

    @Test
    @DisplayName("Save creates contact when Successful")
    void save_PersistContact_WhenSuccessful(){
        Contact contactToBeSaved = ContactCreator.createContactToSaved();
        Contact contactSaved = this.contactRepository.save(contactToBeSaved);
        Assertions.assertThat(contactSaved).isNotNull();
        Assertions.assertThat(contactSaved.getId()).isNotNull();
        Assertions.assertThat(contactSaved.getName()).isEqualTo(contactToBeSaved.getName());
    }

    @Test
    @DisplayName("Save updates contact when Successful")
    void save_UpdateContact_WhenSuccessful(){
        Contact contactToBeSaved = ContactCreator.createContactToSaved();
        Contact contactSaved = this.contactRepository.save(contactToBeSaved);

        contactSaved.setNumber("55777777777");
        Contact contactUpdated = this.contactRepository.save(contactSaved);
        log.info(contactUpdated.getNumber());
        Assertions.assertThat(contactSaved).isNotNull();
        Assertions.assertThat(contactSaved.getId()).isNotNull();
        Assertions.assertThat(contactUpdated.getName()).isEqualTo(contactToBeSaved.getName());
    }
    @Test
    @DisplayName("Deletes removes contact when Successful")
    void delete_RemovesContact_WhenSuccessful(){
        Contact contactToBeSaved = ContactCreator.createContactToSaved();
        Contact contactSaved = this.contactRepository.save(contactToBeSaved);

        this.contactRepository.delete(contactSaved);
        Optional<Contact> contactOptional = this.contactRepository.findById(contactSaved.getId());
        Assertions.assertThat(contactOptional).isEmpty();

    }

    @Test
    @DisplayName("Find by contact when Successful")
    void find_ByContact_WhenSuccessful(){
        Contact contactToBeSaved = ContactCreator.createContactToSaved();
        Contact contactSaved = this.contactRepository.save(contactToBeSaved);
        Contact contactFind = this.contactRepository.findContactById(contactSaved.getId());

        Assertions.assertThat(contactFind).isNotNull();
        Assertions.assertThat(contactFind.getId()).isNotNull();

    }
    @Test
    @DisplayName("Find by contact return null when no contact not found")
    void findByContact_ReturnsNull_WhenContactIsNotFound() {
        Contact contactToBeSaved = ContactCreator.createContactToSaved();
        Contact contactFind = this.contactRepository.findContactById(contactToBeSaved.getId());

        Assertions.assertThat(contactFind).isNull();
    }

    @Test
    @DisplayName("Save throw ConstraintViolidationException when name is empty")
    void save_ConstraintViolidationException_WhenNameIsEmpty(){
        Contact contact = new Contact();
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.contactRepository.save(contact))
                .withMessageContaining("The name cannot be empty");
    }
}