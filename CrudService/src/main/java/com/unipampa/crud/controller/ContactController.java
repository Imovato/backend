package com.unipampa.crud.controller;


import com.unipampa.crud.dto.ContactDTO;
import com.unipampa.crud.interfaces.service.IContactService;
import com.unipampa.crud.model.Contact;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/contact")
@Api(value = "API Contato")
public class ContactController {

	private IContactService contactService;

	public ContactController(IContactService service) {
		this.contactService = service;
	}

    @PostMapping("contact")
	@ApiOperation(value = "Salva uma requisição de contato com a imobiliária")
	public void saveContact(@RequestBody ContactDTO contactDto) {
		Contact contact = new Contact();
		contact.setMessage(contactDto.getMessage());
		contact.setName(contactDto.getName());
		contact.setEmail(contactDto.getEmail());
		contact.setNumber(contactDto.getNumber());
		contactService.saveContact(contact);
	}
}
