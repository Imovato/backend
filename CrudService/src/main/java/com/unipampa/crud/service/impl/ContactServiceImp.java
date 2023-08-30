package com.unipampa.crud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unipampa.crud.service.IContactService;
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

	@Override
	public Contact updateContact(Contact contact) {
		return contactRepository.save(contact);
	}

	@Override
	public void deleteContact(Long id) {
		contactRepository.deleteById(id);
	}

	@Override
	public List<Contact> findAllContacts() {
		return contactRepository.findAll();
	}

	@Override
	public Contact findContactById(Long id) {
		return contactRepository.findContactById(id);
	}
}