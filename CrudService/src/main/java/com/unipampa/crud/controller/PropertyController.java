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
import com.unipampa.crud.service.IPropertyService;
import com.unipampa.crud.model.Apartment;
import com.unipampa.crud.model.Ground;
import com.unipampa.crud.model.House;
import com.unipampa.crud.model.Hosting;
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

	
//	@PostMapping("/add")
//	@ApiOperation(value = "Salva uma propriedade definindo o seu tipo com base nas suas propriedades (e. g. se não houverem quartos será salvo como terreno)")
//	public ResponseEntity<Void> saveProperty(@RequestBody PropertyDTO propertyDTO) {
//
//		if(propertyDTO.getRooms() == null) {
//			Ground ground = (Ground) Ground.builder()
//					.area(propertyDTO.getArea())
//					.name(propertyDTO.getName())
//					.neighborhood(propertyDTO.getNeighborhood())
//					.codAddress(propertyDTO.getCodAddress())
//					.city(propertyDTO.getCity())
//					.description(propertyDTO.getDescription())
//					.adress(propertyDTO.getAdress())
//					.state(propertyDTO.getState())
//					.price(propertyDTO.getPrice())
//					.number(propertyDTO.getNumber())
//					.amount(propertyDTO.getAmount()).build();
//		   // Property property = propertyService.strategySave(propertyDTO);
//			propertyService.saveProperty(ground);
//			return new ResponseEntity<>(HttpStatus.CREATED);
//
//		} else if (propertyDTO.getBlock() == null) {
//			House house = House.builder()
//					.area(propertyDTO.getArea())
//					.name(propertyDTO.getName())
//					.neighborhood(propertyDTO.getNeighborhood())
//					.codAddress(propertyDTO.getCodAddress())
//					.city(propertyDTO.getCity())
//					.description(propertyDTO.getDescription())
//					.adress(propertyDTO.getAdress())
//					.state(propertyDTO.getState())
//					.price(propertyDTO.getPrice())
//					.number(propertyDTO.getNumber())
//					.rooms(propertyDTO.getRooms())
//					.amount(propertyDTO.getAmount()).build();
//			propertyService.saveProperty(house);
//			return new ResponseEntity<>(HttpStatus.CREATED);
//
//		} else {
//			Apartment apartment = Apartment.builder()
//					.area(propertyDTO.getArea())
//					.name(propertyDTO.getName())
//					.neighborhood(propertyDTO.getNeighborhood())
//					.codAddress(propertyDTO.getCodAddress())
//					.city(propertyDTO.getCity())
//					.description(propertyDTO.getDescription())
//					.adress(propertyDTO.getAdress())
//					.state(propertyDTO.getState())
//					.price(propertyDTO.getPrice())
//					.number(propertyDTO.getNumber())
//					.block(propertyDTO.getBlock())
//					.rooms(propertyDTO.getRooms())
//					.amount(propertyDTO.getAmount()).build();
//			propertyService.saveProperty(apartment);
//			return new ResponseEntity<>(HttpStatus.CREATED);
//		}
//	}
	
	@GetMapping("property/all")
	@ApiOperation(value = "Retorna uma lista com todas as propriedades")
	public ResponseEntity<List<Hosting>> getAllProperties() {
		List<Hosting> properties = propertyService.findAllProperties();
		return ResponseEntity.status(HttpStatus.OK).body(properties);
	}
	
	@GetMapping("property/find/{id}")
	@ApiOperation(value = "Encontra uma propriedade através do id")
	public ResponseEntity<?> getPropertyById(@PathVariable("id") Long id) {
		Hosting hosting = propertyService.getPropertyById(id);
		return new ResponseEntity<>(hosting, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	@ApiOperation(value = "Deleta uma propriedade através do id")
	public ResponseEntity<Void> deleteProperty(@PathVariable("id") Long id) {
		propertyService.deleteProperty(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("property/upload/{id}")
	@ApiOperation(value = "Sobe três imagens para a propriedade")
	public ResponseEntity<?> uploadImage(
		@PathVariable("id") Long id, 
		@RequestParam("img1") MultipartFile img1,
		@RequestParam("img2") Optional<MultipartFile> img2, 
		@RequestParam("img3") Optional<MultipartFile> img3) throws IOException {
		Hosting hosting = propertyService.getPropertyById(id);
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

		hosting.setImageQuantity((int) Files.walk(Paths.get(uploadDir)).count() - 1);
		propertyService.updateProperty(hosting);

		return new ResponseEntity<>(hosting, HttpStatus.OK);
	}

	// ----------------------------
	// --- APARTMENTS ENDPOINTS ---
	// ----------------------------
	
//	@PostMapping("/apartment")
//	@ApiOperation(value = "Salva um apartamento")
//	public ResponseEntity<Void> saveApartment(@RequestBody PropertyDTO apartmentDto) {
//		Apartment apartment = Apartment.builder()
//				.area(apartmentDto.getArea())
//				.name(apartmentDto.getName())
//				.neighborhood(apartmentDto.getNeighborhood())
//				.codAddress(apartmentDto.getCodAddress())
//				.city(apartmentDto.getCity())
//				.description(apartmentDto.getDescription())
//				.adress(apartmentDto.getAdress())
//				.state(apartmentDto.getState())
//				.price(apartmentDto.getPrice())
//				.number(apartmentDto.getNumber())
//				.block(apartmentDto.getBlock())
//				.rooms(apartmentDto.getRooms())
//				.amount(apartmentDto.getAmount()).build();
//		propertyService.saveProperty(apartment);
//		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//	}
	
	@GetMapping("apartment/all")
	@ApiOperation(value = "Retorna uma lista de apartamentos")
	public ResponseEntity<List<Hosting>> getAllApartments() {
		List<Hosting> properties = propertyService.findAllByDtype("Apartment");
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
	
//	@PostMapping("/house")
//	@ApiOperation(value = "Salva uma casa ")
//	public ResponseEntity<?> saveHouse(@RequestBody PropertyDTO houseDto) {
//
//		House house = House.builder()
//				.area(houseDto.getArea())
//				.name(houseDto.getName())
//				.neighborhood(houseDto.getNeighborhood())
//				.codAddress(houseDto.getCodAddress())
//				.city(houseDto.getCity())
//				.description(houseDto.getDescription())
//				.adress(houseDto.getAdress())
//				.state(houseDto.getState())
//				.price(houseDto.getPrice())
//				.number(houseDto.getNumber())
//				.rooms(houseDto.getRooms())
//				.amount(houseDto.getAmount())
//				.build();
//		propertyService.saveProperty(house);
//		return new ResponseEntity<>(house, HttpStatus.OK);
//	}

	@GetMapping("house/all")
	@ApiOperation(value = "Retorna uma lista de casas")
	public ResponseEntity<?> getAllHouses() {
		List<Hosting> properties = propertyService.findAllByDtype("House");
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
	
//	@PostMapping("/ground")
//	@ApiOperation(value = "Salva um terreno")
//	public ResponseEntity<?> saveGround(@RequestBody PropertyDTO groundDto) {
//
//		Ground ground = (Ground) Ground.builder()
//				.area(groundDto.getArea())
//				.name(groundDto.getName())
//				.neighborhood(groundDto.getNeighborhood())
//				.codAddress(groundDto.getCodAddress())
//				.city(groundDto.getCity())
//				.description(groundDto.getDescription())
//				.adress(groundDto.getAdress())
//				.state(groundDto.getState())
//				.price(groundDto.getPrice())
//				.number(groundDto.getNumber())
//				.amount(groundDto.getAmount())
//				.build();
//		propertyService.saveProperty(ground);
//		return new ResponseEntity<>(ground, HttpStatus.OK);
//	}

	@GetMapping("ground/all")
	@ApiOperation(value = "Retorna uma lista de terrenos")
	public ResponseEntity<?> getAllGrounds() {
		List<Hosting> properties = propertyService.findAllByDtype("Ground");
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
