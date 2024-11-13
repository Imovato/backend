package com.unipampa.crud.service;

import com.unipampa.crud.entities.Accommodation;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {

	void save(Accommodation hosting);

	Optional<Accommodation> findById(String id);

	List<Accommodation> findAll();

	void delete(String id);

	boolean existsByCodAddressAndNumber(String codeAddress, int number);

}
