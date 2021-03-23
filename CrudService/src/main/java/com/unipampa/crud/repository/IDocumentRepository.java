package com.unipampa.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unipampa.crud.model.Document;

public interface IDocumentRepository extends JpaRepository<Document, Long> {

}
