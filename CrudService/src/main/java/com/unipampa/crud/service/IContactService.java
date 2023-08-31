package com.unipampa.crud.service;

import java.util.List;

import com.unipampa.crud.model.Contact;

public interface IContactService{

    void saveContact(Contact contact);
    Contact updateContact(Contact contact);
    void deleteContact(Long id);
    List<Contact> findAllContacts();
    Contact findContactById(Long id);
}
