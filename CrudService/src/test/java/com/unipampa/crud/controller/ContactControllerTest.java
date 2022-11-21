package com.unipampa.crud.controller;

import com.unipampa.crud.interfaces.service.IContactService;
import com.unipampa.crud.model.Contact;
import com.unipampa.util.ContactCreator;
import com.unipampa.util.ContactDTO1;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@ExtendWith(SpringExtension.class)
@Slf4j
class ContactControllerTest {
    @InjectMocks
    private ContactController contactController;
    @Mock
    private IContactService iContactService;

    @BeforeEach
    void setUp(){
        BDDMockito.when(iContactService.findAllContacts())
                .thenReturn(Arrays.asList(ContactCreator.createValidContact()));

        BDDMockito.when(iContactService.findContactById(ArgumentMatchers.anyLong()))
                .thenReturn(ContactCreator.createValidContact());

        BDDMockito.doNothing().when(iContactService).saveContact(ArgumentMatchers.any(Contact.class));

        BDDMockito.when(iContactService.updateContact(ArgumentMatchers.any(Contact.class)))
                .thenReturn(ContactCreator.createValidUpdateContact());

        BDDMockito.doNothing().when(iContactService).deleteContact(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("ListAll returns list of contacts when successful")
    void listAll_ReturnsListOfContacts_WhenSuccessful() {
        String expectedName = ContactCreator.createValidContact().getName();
        List<Contact> contacts = (List<Contact>) contactController.getAllContacts().getBody();
        Assertions.assertThat(contacts)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(contacts.get(0).getName()).isEqualTo(expectedName);
    }
    @Test
    @DisplayName("ListAll returns an empty list of contacts when contact is not found")
    void listAll_ReturnsEmptyListOfAnime_WhenAnimeIsNotFound() {
        BDDMockito.when(iContactService.findAllContacts())
                .thenReturn(Collections.emptyList());

        List<Contact> contacts = (List<Contact>) contactController.getAllContacts().getBody();
        Assertions.assertThat(contacts)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("Find By Id return contact when successful")
    void findById_ReturnContact_WhenSuccessful() {
        Long expectedId = ContactCreator.createValidContact().getId();

        Contact contact = (Contact) contactController.getContactById(1L).getBody();

        Assertions.assertThat(contact)
                .isNotNull();
        Assertions.assertThat(contact.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("Save contact when successful")
    void save_Contact_WhenSuccessful() {

        Assertions.assertThatCode(() -> contactController.saveContact(ContactDTO1.createContactPostRequestBody()))
                        .doesNotThrowAnyException();
        ResponseEntity<Void> entity = contactController.saveContact(ContactDTO1.createContactPostRequestBody());
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    @DisplayName("Update return contact when successful")
    void update_ReturnContact_WhenSuccessful() {
        Contact contact = contactController.updateContact(ContactPut.createContactPutRequestBody());
        Assertions.assertThat(contact).isNotNull().isEqualTo(ContactCreator.createValidUpdateContact());

    }

    @Test
    @DisplayName("Delete removes contact when successful")
    void delete_RemovesContact_WhenSuccessful() {
        Assertions.assertThatCode(() -> contactController.deleteContact(1L))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = contactController.deleteContact(1L);

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }

}