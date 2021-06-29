package com.unipampa.crud.interfaces.service;

import java.util.List;

import com.unipampa.crud.dto.PropertyDTO;
import com.unipampa.crud.model.Apartment;
import com.unipampa.crud.model.Ground;
import com.unipampa.crud.model.House;
import com.unipampa.crud.model.Property;

public interface IPropertyService {

	void saveProperty(Property property);

	public House updateHouse(House house);

	public Apartment updateApartment(Apartment apartment);

	public Ground updateGround(Ground ground);

	public Property updateProperty(Property property);

	public House getHouseById(Long id);
	
	public Apartment getApartmentById(Long id);
	
	public Ground getGroundById(Long id);
	
	public Property getPropertyById(Long id);

	public List<Property> findAllProperties();

	public List<Property> findAllByDtype(String dtype);
	
	public void deleteProperty(Long id);

	public PropertyDTO createApartment(PropertyDTO propertyDTO);

	public PropertyDTO createHouse(PropertyDTO propertyDTO);

	public PropertyDTO createGround(PropertyDTO propertyDTO);
}
