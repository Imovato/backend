package com.unipampa.scheduling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unipampa.scheduling.model.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

}
