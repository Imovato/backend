package com.unipampa.crud.implement.service;

import java.util.List;

import com.unipampa.crud.dto.PropertyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unipampa.crud.interfaces.service.IPropertyService;
import com.unipampa.crud.model.Apartment;
import com.unipampa.crud.model.Ground;
import com.unipampa.crud.model.House;
import com.unipampa.crud.model.Property;
import com.unipampa.crud.repository.PropertyRepository;
import com.unipampa.crud.sender.PropertySender;

@Service
public class PropertyServiceImp implements IPropertyService {

	private PropertyRepository propertyRepository;
	private PropertySender propertySender;

	@Autowired
	public PropertyServiceImp(PropertyRepository repository, PropertySender sendMessage) {
		this.propertyRepository = repository;
		this.propertySender = sendMessage;
	}

	@Override
	@Transactional
	public void saveProperty(Property property) {
		Property propertySaved = propertyRepository.save(property);
		propertySender.sendMessage(propertySaved);
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
	public List<Property> findAllByDtype(String dtype) {
		return propertyRepository.findAllByDtype(dtype);
	}

	@Override
	public void deleteProperty(Long id) {
		propertyRepository.deleteById(id);

	}

	@Override
	public Property getPropertyById(Long id) {
		return propertyRepository.findPropertyById(id);
	}

	@Override
	public Property updateProperty(Property property) {
		return propertyRepository.save(property);
	}

	@Override
	public PropertyDTO createApartment(PropertyDTO propertyDTO) {
		return PropertyDTO.createApartment(propertyRepository.save(Apartment.createApartment(propertyDTO)));

	}

	@Override
	public PropertyDTO createHouse(PropertyDTO propertyDTO) {
		return PropertyDTO.createHouse(propertyRepository.save(House.createHouse(propertyDTO)));
	}

	@Override
	public PropertyDTO createGround(PropertyDTO propertyDTO) {
		return PropertyDTO.createGround(propertyRepository.save(Ground.createGround(propertyDTO)));
	}
}
