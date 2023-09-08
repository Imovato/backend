package com.unipampa.crud.service;

import com.unipampa.crud.model.Accommodation;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {

	void save(Accommodation hosting);

	Optional<Accommodation> findById(Long id);

	List<Accommodation> findAll();

	void delete(Long id);

}
