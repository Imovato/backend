package com.unipampa.crud.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unipampa.crud.dto.PropertyDTO;
import com.unipampa.crud.interfaces.service.IPropertyService;
import com.unipampa.crud.model.Property;

@RestController
@RequestMapping("/api/property")
public class PropertyController {

	private IPropertyService propertyService;

	public PropertyController(IPropertyService service) {
		this.propertyService = service;
	}

	@PostMapping
	public void saveProperty(@RequestBody PropertyDTO propertyDto) {
		Property property = new Property();
		property.setArea(propertyDto.getArea());
		property.setNeighborhood(propertyDto.getNeighborhood());
		property.setCodAddress(propertyDto.getCodAddress());
		property.setCity(propertyDto.getCity());
		property.setDescription(propertyDto.getDescription());
		property.setAdress(propertyDto.getAdress());
		property.setState(propertyDto.getState());
		property.setPrice(propertyDto.getPrice());
		propertyService.saveProperty(property);

	}
}
