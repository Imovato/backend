package com.unipampa.crud.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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
import com.unipampa.crud.utils.FileUploadUtil;

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

	// --------------------------------
	// --- ALL PROPERTIES ENDPOINTS ---
	// --------------------------------

	@PostMapping("/add")
	@ApiOperation(value = "Salva uma propriedade definindo o seu tipo com base nas suas propriedades (e. g. se não houverem quartos será salvo como terreno)")
	public ResponseEntity<?> saveProperty(@RequestBody PropertyDTO propertyDTO) {
		if (propertyDTO.getRooms() == null) {
			PropertyDTO dtoReturn = propertyService.createGround(propertyDTO);
			return new ResponseEntity<>(dtoReturn, HttpStatus.OK);
		} else if (propertyDTO.getBlock() == null) {
			PropertyDTO dtoReturn = propertyService.createApartment(propertyDTO);
			return new ResponseEntity<>(dtoReturn, HttpStatus.OK);
		} else {
			PropertyDTO dtoReturn = propertyService.createHouse(propertyDTO);
			return new ResponseEntity<>(dtoReturn, HttpStatus.OK);
		}
	}

	@GetMapping("property/all")
	@ApiOperation(value = "Retorna uma lista com todas as propriedades")
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

	@DeleteMapping("/delete/{id}")
	@ApiOperation(value = "Deleta uma propriedade através do id")
	public void deleteProperty(@PathVariable("id") Long id) {
		propertyService.deleteProperty(id);
	}

	@PostMapping("property/upload/{id}")
	@ApiOperation(value = "Sobe três imagens para a propriedade")
	public ResponseEntity<?> uploadImage(@PathVariable("id") Long id, @RequestParam("img1") MultipartFile img1,
			@RequestParam("img2") Optional<MultipartFile> img2, @RequestParam("img3") Optional<MultipartFile> img3)
			throws IOException {
		Property property = propertyService.getPropertyById(id);
		String uploadDir = "src/main/resources/static/images/property/" + id;
		Files.createDirectories(Paths.get(uploadDir));

		Stream<Path> files1 = Files.walk(Paths.get(uploadDir));
		FileUploadUtil.saveFile(uploadDir, files1.count() + ".jpg", img1);
		Stream<Path> files2 = Files.walk(Paths.get(uploadDir));
		img2.ifPresent(img -> FileUploadUtil.saveFile(uploadDir, files2.count() + ".jpg", img));
		Stream<Path> files3 = Files.walk(Paths.get(uploadDir));
		img3.ifPresent(img -> FileUploadUtil.saveFile(uploadDir, files3.count() + ".jpg", img));

		files1.close();
		files2.close();
		files3.close();

		property.setImageQuantity((int) Files.walk(Paths.get(uploadDir)).count() - 1);
		propertyService.updateProperty(property);

		return new ResponseEntity<>(property, HttpStatus.OK);
	}

	// ----------------------------
	// --- APARTMENTS ENDPOINTS ---
	// ----------------------------
	@PostMapping("/apartment")
	@ApiOperation(value = "Salva um apartamento")
	public ResponseEntity<?> saveApartment(@RequestBody PropertyDTO apartmentDto) {
		PropertyDTO dtoReturn = propertyService.createApartment(apartmentDto);
		return new ResponseEntity<>(dtoReturn, HttpStatus.OK);
	}

	@GetMapping("apartment/all")
	@ApiOperation(value = "Retorna uma lista de apartamentos")
	public ResponseEntity<?> getAllApartments() {
		List<Property> properties = propertyService.findAllByDtype("Apartment");
		return new ResponseEntity<>(properties, HttpStatus.OK);
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

	// ------------------------
	// --- HOUSES ENDPOINTS ---
	// ------------------------

	@PostMapping("/house")
	@ApiOperation(value = "Salva uma casa ")
	public ResponseEntity<?> saveHouse(@RequestBody PropertyDTO houseDto) {
		PropertyDTO dtoReturn = propertyService.createHouse(houseDto);
		return new ResponseEntity<>(dtoReturn, HttpStatus.OK);
	}

	@GetMapping("house/all")
	@ApiOperation(value = "Retorna uma lista de casas")
	public ResponseEntity<?> getAllHouses() {
		List<Property> properties = propertyService.findAllByDtype("House");
		return new ResponseEntity<>(properties, HttpStatus.OK);
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

	// -------------------------
	// --- GROUNDS ENDPOINTS ---
	// -------------------------

	@PostMapping("/ground")
	@ApiOperation(value = "Salva um terreno")
	public ResponseEntity<?> saveGround(@RequestBody PropertyDTO groundDto) {
		PropertyDTO dtoReturn = propertyService.createGround(groundDto);
		return new ResponseEntity<>(dtoReturn, HttpStatus.OK);
	}


	@GetMapping("ground/all")
	@ApiOperation(value = "Retorna uma lista de terrenos")
	public ResponseEntity<?> getAllGrounds() {
		List<Property> properties = propertyService.findAllByDtype("Ground");
		return new ResponseEntity<>(properties, HttpStatus.OK);
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
}
