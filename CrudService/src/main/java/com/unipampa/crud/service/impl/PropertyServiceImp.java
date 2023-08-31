package com.unipampa.crud.service.impl;

import java.util.List;

import com.unipampa.crud.dto.PropertyDTO;
import com.unipampa.crud.service.StrategySaveProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unipampa.crud.service.IPropertyService;
import com.unipampa.crud.model.Apartment;
import com.unipampa.crud.model.Ground;
import com.unipampa.crud.model.House;
import com.unipampa.crud.model.Hosting;
import com.unipampa.crud.repository.PropertyRepository;
import com.unipampa.crud.sender.PropertySender;

@Service
public class PropertyServiceImp implements IPropertyService {

	private PropertyRepository propertyRepository;
	private PropertySender propertySender;

	private StrategySaveProperty strategySaveProperty;

	@Autowired
	public PropertyServiceImp(PropertyRepository repository, PropertySender sendMessage) {
		this.propertyRepository = repository;
		this.propertySender = sendMessage;
	}

	@Override
	@Transactional
	public void saveProperty(Hosting hosting) {
		Hosting hostingSaved = propertyRepository.save(hosting);
		propertySender.sendMessage(hostingSaved);
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
	public List<Hosting> findAllProperties() {
		return propertyRepository.findAll();
	}

	@Override
	public List<Hosting> findAllByDtype(String dtype) {
		return propertyRepository.findAllByDtype(dtype);
	}

	@Override
	public void deleteProperty(Long id) {
		propertyRepository.deleteById(id);

	}

	@Override
	public Hosting getPropertyById(Long id) {
		return propertyRepository.findPropertyById(id);
	}

	@Override
	public Hosting updateProperty(Hosting hosting) {
		return propertyRepository.save(hosting);
	}

	@Override
	public Hosting strategySave(PropertyDTO propertyDTO){

		if(propertyDTO.getRooms() == null) {
			strategySaveProperty = new GroundSave();
			return (Hosting) strategySaveProperty;
		} else if (propertyDTO.getBlock() == null) {
			strategySaveProperty = new HouseSave();
			return (Hosting) strategySaveProperty;
		} else {
			strategySaveProperty = new ApartmentSave();
			return (Hosting) strategySaveProperty;
		}
	}

}
