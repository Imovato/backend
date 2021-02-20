package com.unipampa.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unipampa.crud.model.Property;

public interface PropertyRepository extends JpaRepository<Property, Long>{

}
