package com.example.acquisition.interfaces.services;

import com.example.acquisition.model.Property;

public interface IPropertyService{
	Property findPropertyById(Long id);
	Double calculaParcela(Property property);
	void updateProperty(Property property);
}
