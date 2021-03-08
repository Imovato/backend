package com.unipampa.crud.implement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unipampa.crud.interfaces.service.IPropertyService;
import com.unipampa.crud.model.Apartment;
import com.unipampa.crud.model.Ground;
import com.unipampa.crud.model.House;
import com.unipampa.crud.model.Property;
import com.unipampa.crud.repository.PropertyRepository;

@Service
public class PropertyServiceImp implements IPropertyService {

	private PropertyRepository propertyRepository;

	@Autowired
	public PropertyServiceImp(PropertyRepository repository) {
		this.propertyRepository = repository;
	}

	@Override
	@Transactional
	public void saveProperty(Property property) {
		propertyRepository.save(property);
	}

	@Override
	public House updateHouse(House house) {
		return propertyRepository.save(house);
	}

	@Override
	public Apartment updateApartment(Apartment apartment) {
		return propertyRepository.save(apartment);
	}

	@Override
	public Ground updateGround(Ground ground) {
		return propertyRepository.save(ground);
	}

	@Override
	public House getHouseById(Long id) {
		return propertyRepository.findHouseById(id);
	}

	@Override
	public Apartment getApartmentById(Long id) {
		return propertyRepository.findApartmentById(id);
	}

	@Override
	public Ground getGroundById(Long id) {
		return propertyRepository.findGroundById(id);
	}

	@Override
	public List<Property> findAllProperties() {
		return propertyRepository.findAll();
	}

	@Override
	public void deleteProperty(Long id) {
		propertyRepository.deleteById(id);

	}

}
