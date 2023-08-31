package com.unipampa.crud.service;

import com.unipampa.crud.dto.PropertyDTO;
import com.unipampa.crud.model.Apartment;
import com.unipampa.crud.model.Ground;
import com.unipampa.crud.model.House;
import com.unipampa.crud.model.Hosting;

import java.util.List;

public interface IPropertyService {

	void saveProperty(Hosting hosting);

	public House updateHouse(House house);

	public Apartment updateApartment(Apartment apartment);

	public Ground updateGround(Ground ground);

	public Hosting updateProperty(Hosting hosting);

	public House getHouseById(Long id);
	
	public Apartment getApartmentById(Long id);
	
	public Ground getGroundById(Long id);
	
	public Hosting getPropertyById(Long id);

	public List<Hosting> findAllProperties();

	public List<Hosting> findAllByDtype(String dtype);
	
	public void deleteProperty(Long id);

	public Hosting strategySave(PropertyDTO propertyDTO);
}
