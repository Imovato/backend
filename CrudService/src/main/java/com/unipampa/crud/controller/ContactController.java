package com.unipampa.crud.controller;

import com.unipampa.crud.dto.ContactDTO;
import com.unipampa.crud.interfaces.service.IContactService;
import com.unipampa.crud.model.Contact;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<?> saveContact(@RequestBody ContactDTO contactDto) {
        ContactDTO dtoReturn = contactService.createContact(contactDto);
        return new ResponseEntity<>(dtoReturn, HttpStatus.OK);
    }

    @PutMapping("/update")
    @ApiOperation(value = "Atualiza uma requisição de contato com a imobiliária")
    public Contact updateContact(Contact contact) {
        return contactService.updateContact(contact);
    }

    @GetMapping("/all")
    @ApiOperation(value = "Retorna todas requisições de contato com a imobiliária")
    public ResponseEntity<?> getAllContacts() {
        List<Contact> contacts = contactService.findAllContacts();
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Retorna todas requisições de contato com a imobiliária")
    public ResponseEntity<?> getContactById(@PathVariable("id") Long id) {
        Contact contact = contactService.findContactById(id);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Deleta uma requisição de contato com a imobiliária")
    public void deleteContact(@PathVariable("id") Long id) {
        contactService.deleteContact(id);
    }
}
