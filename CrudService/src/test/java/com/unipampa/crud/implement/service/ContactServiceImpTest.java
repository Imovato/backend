package com.unipampa.crud.implement.service;

import com.unipampa.crud.model.Contact;
import com.unipampa.crud.repository.ContactRepository;
import com.unipampa.crud.service.impl.ContactServiceImp;
import com.unipampa.util.ContactCreator;
import com.unipampa.util.ContactPut;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ExtendWith(SpringExtension.class)
@Slf4j
class ContactServiceImpTest {
    @InjectMocks
    private ContactServiceImp contactServiceImp;
    @Mock
    private ContactRepository contactRepository;

    @BeforeEach
    void setUp(){
        BDDMockito.when(contactRepository.findAll())
                .thenReturn(Arrays.asList(ContactCreator.createValidContact()));

        BDDMockito.when(contactRepository.findContactById(ArgumentMatchers.anyLong()))
                .thenReturn(ContactCreator.createValidContact());

//        BDDMockito.doNothing().when(contactRepository).save(ArgumentMatchers.any(Contact.class));

        BDDMockito.when(contactRepository.save(ArgumentMatchers.any(Contact.class)))
                .thenReturn(ContactCreator.createValidUpdateContact());

        BDDMockito.doNothing().when(contactRepository).delete(ArgumentMatchers.any(Contact.class));
    }

    @Test
    @DisplayName("ListAll returns list of contacts when successful")
    void listAll_ReturnsListOfContacts_WhenSuccessful() {
        String expectedName = ContactCreator.createValidContact().getName();

        List<Contact> contacts = contactServiceImp.findAllContacts();
        Assertions.assertThat(contacts)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(contacts.get(0).getName()).isEqualTo(expectedName);
    }
    @Test
    @DisplayName("ListAll returns an empty list of contacts when contact is not found")
    void listAll_ReturnsEmptyListOfAnime_WhenAnimeIsNotFound() {
        BDDMockito.when(contactRepository.findAll())
                .thenReturn(Collections.emptyList());

        List<Contact> contacts = contactServiceImp.findAllContacts();
        Assertions.assertThat(contacts)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("Find By Id return contact when successful")
    void findById_ReturnContact_WhenSuccessful() {
        Long expectedId = ContactCreator.createValidContact().getId();

        Contact contact = contactServiceImp.findContactById(1L);
        log.info(contact.getName());
        Assertions.assertThat(contact)
                .isNotNull();
        Assertions.assertThat(contact.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("Save contact when successful")
    void save_Contact_WhenSuccessful() {

        Assertions.assertThatCode(() -> contactServiceImp.saveContact(ContactPut.createContactPutRequestBody()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Update return contact when successful")
    void update_ReturnContact_WhenSuccessful() {
        Contact contact = contactServiceImp.updateContact(ContactPut.createContactPutRequestBody());
        Assertions.assertThat(contact).isNotNull().isEqualTo(ContactCreator.createValidUpdateContact());

    }

    @Test
    @DisplayName("Delete removes contact when successful")
    void delete_RemovesContact_WhenSuccessful() {
        Assertions.assertThatCode(() -> contactServiceImp.deleteContact(1L))
                .doesNotThrowAnyException();
    }
}