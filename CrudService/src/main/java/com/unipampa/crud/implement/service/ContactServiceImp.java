package com.unipampa.crud.implement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unipampa.crud.interfaces.service.IContactService;
import com.unipampa.crud.model.Contact;
import com.unipampa.crud.repository.ContactRepository;

@Service
public class ContactServiceImp implements IContactService {

	private ContactRepository contactRepository;

	@Autowired
	public ContactServiceImp(ContactRepository repository) {
		this.contactRepository = repository;
	}

	@Override
	@Transactional
	public void saveContact(Contact contact) {
		contactRepository.save(contact);
	}
}