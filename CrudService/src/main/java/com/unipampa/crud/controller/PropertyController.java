package com.unipampa.crud.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unipampa.crud.dto.PropertyDTO;
import com.unipampa.crud.interfaces.service.IPropertyService;
import com.unipampa.crud.model.Apartment;
import com.unipampa.crud.model.Ground;
import com.unipampa.crud.model.House;
import com.unipampa.crud.model.Property;

@RestController
@RequestMapping("/api/property")
public class PropertyController {

	private IPropertyService propertyService;

	public PropertyController(IPropertyService service) {
		this.propertyService = service;
	}

	@GetMapping("property/all")
	public ResponseEntity<?> getAllProperties() {
		List<Property> properties = propertyService.findAllProperties();
		return new ResponseEntity<>(properties, HttpStatus.OK);
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

	@GetMapping("house/find/{id}")
	public ResponseEntity<?> getHouseById(@PathVariable("id") Long id) {
		House house = propertyService.getHouseById(id);
		return new ResponseEntity<>(house, HttpStatus.OK);
	}

	@PutMapping("/update/house")
	public ResponseEntity<?> updateHouse(@RequestBody House house) {
		House updatedHouse = propertyService.updateHouse(house);
		return new ResponseEntity<>(updatedHouse, HttpStatus.OK);
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

	@GetMapping("apartment/find/{id}")
	public ResponseEntity<?> getApartmentById(@PathVariable("id") Long id) {
		Apartment apartment = propertyService.getApartmentById(id);
		return new ResponseEntity<>(apartment, HttpStatus.OK);
	}

	@PutMapping("/update/apartment")
	public ResponseEntity<?> updateApartment(@RequestBody Apartment apartment) {
		Apartment updatedApartment = propertyService.updateApartment(apartment);
		return new ResponseEntity<>(updatedApartment, HttpStatus.OK);
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

	@GetMapping("ground/find/{id}")
	public ResponseEntity<?> getGroundById(@PathVariable("id") Long id) {
		Ground ground = propertyService.getGroundById(id);
		return new ResponseEntity<>(ground, HttpStatus.OK);
	}

	@PutMapping("/update/ground")
	public ResponseEntity<?> updateApartment(@RequestBody Ground ground) {
		Ground updatedGround = propertyService.updateGround(ground);
		return new ResponseEntity<>(updatedGround, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteProperty(@PathVariable("id") Long id) {
		propertyService.deleteProperty(id);
	}
}
