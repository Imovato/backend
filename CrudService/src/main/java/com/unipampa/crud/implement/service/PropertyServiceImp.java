package com.unipampa.crud.implement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unipampa.crud.dto.PropertyDTO;
import com.unipampa.crud.interfaces.service.IPropertyService;
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
	
}
