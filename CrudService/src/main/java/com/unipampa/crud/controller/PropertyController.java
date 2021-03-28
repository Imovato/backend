package com.unipampa.crud.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.unipampa.crud.dto.PropertyDTO;
import com.unipampa.crud.interfaces.service.IPropertyService;
import com.unipampa.crud.model.Apartment;
import com.unipampa.crud.model.Ground;
import com.unipampa.crud.model.House;
import com.unipampa.crud.model.Property;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/property")
@Api(value = "API Crud Property")
public class PropertyController {

	private IPropertyService propertyService;

	public PropertyController(IPropertyService service) {
		this.propertyService = service;
	}

	@GetMapping("property/all")
	@ApiOperation(value = "Retorna uma lista de propriedades")
	public ResponseEntity<?> getAllProperties() {
		List<Property> properties = propertyService.findAllProperties();
		return new ResponseEntity<>(properties, HttpStatus.OK);
	}

	@GetMapping("property/find/{id}")
	@ApiOperation(value = "Encontra uma propriedade através do id")
	public ResponseEntity<?> getPropertyById(@PathVariable("id") Long id) {
		Property property = propertyService.getPropertyById(id);
		return new ResponseEntity<>(property, HttpStatus.OK);
	}

	@GetMapping("apartment/all")
	@ApiOperation(value = "Retorna uma lista de apartamentos")
	public ResponseEntity<?> getAllApartments() {
		List<Property> properties = propertyService.findAllByDtype("Apartment");
		return new ResponseEntity<>(properties, HttpStatus.OK);
	}

	@GetMapping("house/all")
	@ApiOperation(value = "Retorna uma lista de casas")
	public ResponseEntity<?> getAllHouses() {
		List<Property> properties = propertyService.findAllByDtype("House");
		return new ResponseEntity<>(properties, HttpStatus.OK);
	}

	@GetMapping("ground/all")
	@ApiOperation(value = "Retorna uma lista de terrenos")
	public ResponseEntity<?> getAllGrounds() {
		List<Property> properties = propertyService.findAllByDtype("Ground");
		return new ResponseEntity<>(properties, HttpStatus.OK);
	}

	@PostMapping("/house")
	@ApiOperation(value = "Salva uma casa ")
	public void saveHouse(@RequestBody PropertyDTO houseDto) {
		House house = new House();
		house.setArea(houseDto.getArea());
		house.setName(houseDto.getName());
		house.setNeighborhood(houseDto.getNeighborhood());
		house.setCodAddress(houseDto.getCodAddress());
		house.setCity(houseDto.getCity());
		house.setDescription(houseDto.getDescription());
		house.setAdress(houseDto.getAdress());
		house.setState(houseDto.getState());
		house.setPrice(houseDto.getPrice());
		house.setNumber(houseDto.getNumber());
		house.setRooms(houseDto.getRooms());
		house.setAmount(houseDto.getAmount());
		propertyService.saveProperty(house);
	}

	@GetMapping("house/find/{id}")
	@ApiOperation(value = "Encontra uma casa através do ID")
	public ResponseEntity<?> getHouseById(@PathVariable("id") Long id) {
		House house = propertyService.getHouseById(id);
		return new ResponseEntity<>(house, HttpStatus.OK);
	}

	@PutMapping("/update/house")
	@ApiOperation(value = "Atualiza as informações de uma casa")
	public ResponseEntity<?> updateHouse(@RequestBody House house) {
		House updatedHouse = propertyService.updateHouse(house);
		return new ResponseEntity<>(updatedHouse, HttpStatus.OK);
	}

	@PostMapping("/apartment")
	@ApiOperation(value = "Salva um apartamento")
	public void saveApartment(@RequestBody PropertyDTO apartmentDto) {
		Apartment apartment = new Apartment();
		apartment.setArea(apartmentDto.getArea());
		apartment.setName(apartmentDto.getName());
		apartment.setNeighborhood(apartmentDto.getNeighborhood());
		apartment.setCodAddress(apartmentDto.getCodAddress());
		apartment.setCity(apartmentDto.getCity());
		apartment.setDescription(apartmentDto.getDescription());
		apartment.setAdress(apartmentDto.getAdress());
		apartment.setState(apartmentDto.getState());
		apartment.setPrice(apartmentDto.getPrice());
		apartment.setNumber(apartmentDto.getNumber());
		apartment.setBlock(apartmentDto.getBlock());
		apartment.setRooms(apartmentDto.getRooms());
		apartment.setAmount(apartmentDto.getAmount());
		propertyService.saveProperty(apartment);
	}

	@GetMapping("apartment/find/{id}")
	@ApiOperation(value = "Encontra um apartamento através do id")
	public ResponseEntity<?> getApartmentById(@PathVariable("id") Long id) {
		Apartment apartment = propertyService.getApartmentById(id);
		return new ResponseEntity<>(apartment, HttpStatus.OK);
	}

	@PutMapping("/update/apartment")
	@ApiOperation(value = "Atualiza as informações de um apartamento")
	public ResponseEntity<?> updateApartment(@RequestBody Apartment apartment) {
		Apartment updatedApartment = propertyService.updateApartment(apartment);
		return new ResponseEntity<>(updatedApartment, HttpStatus.OK);
	}

	@PostMapping("/ground")
	@ApiOperation(value = "Salva um terreno")
	public void saveGround(@RequestBody PropertyDTO groundDto) {
		Ground ground = new Ground();
		ground.setArea(groundDto.getArea());
		ground.setName(groundDto.getName());
		ground.setNeighborhood(groundDto.getNeighborhood());
		ground.setCodAddress(groundDto.getCodAddress());
		ground.setCity(groundDto.getCity());
		ground.setDescription(groundDto.getDescription());
		ground.setAdress(groundDto.getAdress());
		ground.setState(groundDto.getState());
		ground.setPrice(groundDto.getPrice());
		ground.setNumber(groundDto.getNumber());
		ground.setAmount(groundDto.getAmount());
		propertyService.saveProperty(ground);
	}

	@GetMapping("ground/find/{id}")
	@ApiOperation(value = "Encontra um terreno através do id")
	public ResponseEntity<?> getGroundById(@PathVariable("id") Long id) {
		Ground ground = propertyService.getGroundById(id);
		return new ResponseEntity<>(ground, HttpStatus.OK);
	}

	@PutMapping("/update/ground")
	@ApiOperation(value = "Atualiza as informações de um terreno")
	public ResponseEntity<?> updateApartment(@RequestBody Ground ground) {
		Ground updatedGround = propertyService.updateGround(ground);
		return new ResponseEntity<>(updatedGround, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	@ApiOperation(value = "Deleta uma propriedade através do id")
	public void deleteProperty(@PathVariable("id") Long id) {
		propertyService.deleteProperty(id);
	}

	@PostMapping("property/upload/{id}")
	@ApiOperation(value = "Sobe uma imagem para a propriedade")
	public ResponseEntity<?> uploadImage(@PathVariable("id") Long id, @RequestParam("image") MultipartFile img)
			throws IOException {
		Property property = propertyService.getPropertyById(id);
		Path uploadDir = Paths.get("CrudService/src/main/resources/static/images/property/" + id);
		Files.createDirectories(uploadDir);
		long count = Files.walk(uploadDir).count();

		String fileName = count + ".jpg";
		property.setImageQuantity(5);

		try (InputStream inputStream = img.getInputStream()) {
			Path filePath = uploadDir.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ioe) {
			throw new IOException("Could not save image file: " + fileName, ioe);
		}

		propertyService.updateProperty(property);

		return new ResponseEntity<>(property, HttpStatus.OK);
	}

}
