package com.unipampa.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.unipampa.crud.model.Apartment;
import com.unipampa.crud.model.Ground;
import com.unipampa.crud.model.House;
import com.unipampa.crud.model.Property;

public interface PropertyRepository extends JpaRepository<Property, Long>{
	
	House findHouseById(Long id);
	Apartment findApartmentById(Long id);
	Ground findGroundById(Long id);

	Property findPropertyById(Long id);
	public List<Property> findAllByDtype(String dtype);
	
}
