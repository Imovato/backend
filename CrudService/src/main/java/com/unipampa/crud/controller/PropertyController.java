package com.unipampa.crud.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unipampa.crud.dto.PropertyDTO;
import com.unipampa.crud.interfaces.service.IPropertyService;
import com.unipampa.crud.model.Apartment;
import com.unipampa.crud.model.Ground;
import com.unipampa.crud.model.House;

@RestController
@RequestMapping("/api/property")
public class PropertyController {

	private IPropertyService propertyService;

	public PropertyController(IPropertyService service) {
		this.propertyService = service;
	}

	@PostMapping("/house")
	public void saveHouse(@RequestBody PropertyDTO houseDto) {
		House house = new House();
		house.setArea(houseDto.getArea());
		house.setNeighborhood(houseDto.getNeighborhood());
		house.setCodAddress(houseDto.getCodAddress());
		house.setCity(houseDto.getCity());
		house.setDescription(houseDto.getDescription());
		house.setAdress(houseDto.getAdress());
		house.setState(houseDto.getState());
		house.setPrice(houseDto.getPrice());
		house.setNumber(houseDto.getNumber());
		propertyService.saveProperty(house);

	}
	
	@PostMapping("/apartment")
	public void saveApartment(@RequestBody PropertyDTO apartmentDto) {
		Apartment apartment = new Apartment();
		apartment.setArea(apartmentDto.getArea());
		apartment.setNeighborhood(apartmentDto.getNeighborhood());
		apartment.setCodAddress(apartmentDto.getCodAddress());
		apartment.setCity(apartmentDto.getCity());
		apartment.setDescription(apartmentDto.getDescription());
		apartment.setAdress(apartmentDto.getAdress());
		apartment.setState(apartmentDto.getState());
		apartment.setPrice(apartmentDto.getPrice());
		apartment.setNumber(apartmentDto.getNumber());
		apartment.setBlock(apartmentDto.getBlock());
		propertyService.saveProperty(apartment);
	}
	
	@PostMapping("/ground")
	public void saveGround(@RequestBody PropertyDTO groundDto) {
		Ground ground = new Ground();
		ground.setArea(groundDto.getArea());
		ground.setNeighborhood(groundDto.getNeighborhood());
		ground.setCodAddress(groundDto.getCodAddress());
		ground.setCity(groundDto.getCity());
		ground.setDescription(groundDto.getDescription());
		ground.setAdress(groundDto.getAdress());
		ground.setState(groundDto.getState());
		ground.setPrice(groundDto.getPrice());
		ground.setNumber(groundDto.getNumber());
		propertyService.saveProperty(ground);
	}
}
