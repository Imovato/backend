package com.unipampa.crud.repository;

import com.unipampa.crud.dto.AccommodationFilterDTO;
import com.unipampa.crud.entities.Accommodation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AccommodationRepository extends MongoRepository<Accommodation, String>, AccommodationRepositoryCustom {

    boolean existsByZipCodeAndStreetNumber(String codeAddress, int streetNumber);


}
