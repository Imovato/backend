package com.unipampa.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unipampa.crud.model.Apartment;
import com.unipampa.crud.model.Ground;
import com.unipampa.crud.model.House;
import com.unipampa.crud.model.Property;

public interface PropertyRepository extends JpaRepository<Property, Long>{
	
	House findHouseById(Long id);
	Apartment findApartmentById(Long id);
	Ground findGroundById(Long id);
	
}
