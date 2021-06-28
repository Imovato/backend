package com.unipampa.crud.controller;

import com.unipampa.crud.dto.ContactDTO;
import com.unipampa.crud.interfaces.service.IContactService;
import com.unipampa.crud.model.Contact;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    // add
    @PostMapping("contact")
    @ApiOperation(value = "Salva uma requisição de contato com a imobiliária")
    public void saveContact(@RequestBody ContactDTO contactDto) {
        Contact contact = new Contact();
        contact.setMessage(contactDto.message());
        contact.setName(contactDto.name());
        contact.setEmail(contactDto.email());
        contact.setNumber(contactDto.number());
        contactService.saveContact(contact);
    }

    // update
    @PutMapping("/update")
    @ApiOperation(value = "Atualiza uma requisição de contato com a imobiliária")
    public Contact updateContact(Contact contact) {
        return contactService.updateContact(contact);
    }

    // get all
    @GetMapping("/all")
    @ApiOperation(value = "Retorna todas requisições de contato com a imobiliária")
    public ResponseEntity<?> getAllContacts() {
        List<Contact> contacts = contactService.findAllContacts();
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    // get contact by id
    @GetMapping("/{id}")
    @ApiOperation(value = "Retorna todas requisições de contato com a imobiliária")
    public ResponseEntity<?> getContactById(@PathVariable("id") Long id) {
        Contact contact = contactService.findContactById(id);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    // delete
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Deleta uma requisição de contato com a imobiliária")
    public void deleteContact(@PathVariable("id") Long id) {
        contactService.deleteContact(id);
    }
}
