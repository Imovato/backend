package com.unipampa.crud.repository;

import com.unipampa.crud.entities.Accommodation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccommodationRepository extends MongoRepository<Accommodation, String> {

    boolean existsByZipCodeAndNumber(String codeAddress, int number);


}
