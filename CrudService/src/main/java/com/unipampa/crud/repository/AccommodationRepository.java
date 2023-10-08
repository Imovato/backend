package com.unipampa.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unipampa.crud.model.Accommodation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccommodationRepository extends MongoRepository<Accommodation, String> {

}
