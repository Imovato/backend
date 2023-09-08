package com.unipampa.crud.service;

import com.unipampa.crud.model.Accommodation;

import java.util.List;

public interface AccommodationService {

	void save(Accommodation hosting);

	Accommodation findById(Long id);

	List<Accommodation> findAll();

	void delete(Long id);

}
