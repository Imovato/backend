package com.unipampa.crud.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.unipampa.crud.model.Contact;
public interface ContactRepository extends JpaRepository<Contact, Long>{

}