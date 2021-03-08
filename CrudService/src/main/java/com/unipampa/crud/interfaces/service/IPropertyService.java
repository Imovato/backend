package com.unipampa.crud.interfaces.service;

import java.util.List;

import com.unipampa.crud.model.Apartment;
import com.unipampa.crud.model.Ground;
import com.unipampa.crud.model.House;
import com.unipampa.crud.model.Property;

public interface IPropertyService {

	void saveProperty(Property property);

	public House updateHouse(House house);

	public Apartment updateApartment(Apartment apartment);

	public Ground updateGround(Ground ground);
	
	public House getHouseById(Long id);
	
	public Apartment getApartmentById(Long id);
	
	public Ground getGroundById(Long id);
	
	public List<Property> findAllProperties();
	
	public void deleteProperty(Long id);
}
