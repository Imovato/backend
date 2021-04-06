package com.example.acquisition.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.acquisition.interfaces.services.IPropertyService;
import com.example.acquisition.model.Property;
import com.example.acquisition.repository.PropertyRepository;

@Service
public class PropertyServiceImp implements IPropertyService{
	
	private PropertyRepository propertyRepository;
	
	@Autowired
	public PropertyServiceImp(PropertyRepository repository) {
		this.propertyRepository = repository;
	}

	@Override
	public Property findPropertyById(Long id) {
		return propertyRepository.findPropertyById(id);
	}
		
	
}
