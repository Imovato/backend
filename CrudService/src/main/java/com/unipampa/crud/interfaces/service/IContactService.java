package com.unipampa.crud.interfaces.service;

import com.unipampa.crud.dto.ContactDTO;
import com.unipampa.crud.model.Contact;

import java.util.List;

public interface IContactService{

    void saveContact(Contact contact);
    Contact updateContact(Contact contact);
    void deleteContact(Long id);
    List<Contact> findAllContacts();
    Contact findContactById(Long id);

    public ContactDTO createContact(ContactDTO contactDTO);
}
