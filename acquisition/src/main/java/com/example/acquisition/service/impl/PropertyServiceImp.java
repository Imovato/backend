package com.example.acquisition.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.acquisition.model.Property;
import com.example.acquisition.repository.PropertyRepository;
import com.example.acquisition.interfaces.services.IPropertyService;

@Service
public class PropertyServiceImp implements IPropertyService{
	
	private PropertyRepository propertyRepository;
	private final double PERCENTUAL = 0.3;

	@Autowired
	public PropertyServiceImp(PropertyRepository repository) {
		this.propertyRepository = repository;
	}

	@Override
	public Property findPropertyById(Long id) {
		return propertyRepository.findPropertyById(id);
	}
		
	@Override
	public Double calculaParcela(Property property){
		double parcela = property.getPrice() * PERCENTUAL;
		return parcela;
	}
}
