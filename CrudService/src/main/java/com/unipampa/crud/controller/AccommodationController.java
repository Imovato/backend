package com.unipampa.crud.controller;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unipampa.crud.dto.AccommodationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.unipampa.crud.service.AccommodationService;
import com.unipampa.crud.model.Accommodation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/accommodations")
@Api(value = "API Crud Property")
public class AccommodationController {

	@Autowired
	private AccommodationService accommodationService;

	@Autowired
	ObjectMapper mapper;

	@PostMapping("/add")
	@ApiOperation(value = "Salva uma acomodação)")
	public ResponseEntity<Accommodation> save(@RequestBody AccommodationDTO accommodationDTO) {
		var accommodation = mapper.convertValue(accommodationDTO, Accommodation.class);
		accommodationService.save(accommodation);
		return ResponseEntity.status(HttpStatus.OK).body(accommodation);
	}
	
	@GetMapping("/all")
	@ApiOperation(value = "Retorna uma lista com todas as acomodações")
	public ResponseEntity<List<Accommodation>> findAll() {
		List<Accommodation> accommodations = accommodationService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(accommodations);
	}
	
	@GetMapping("/find/{id}")
	@ApiOperation(value = "Encontra uma acomodação através do id")
	public ResponseEntity<Accommodation> getById(@PathVariable("id") Long id) {
		Optional<Accommodation> hosting = accommodationService.findById(id);
		return new ResponseEntity<>(hosting.get(), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	@ApiOperation(value = "Deleta uma acomodação através do id")
	public ResponseEntity<Void> deleteAccommodation(@PathVariable("id") Long id) {
		accommodationService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
//	@PostMapping("property/upload/{id}")
//	@ApiOperation(value = "Sobe três imagens para a propriedade")
//	public ResponseEntity<?> uploadImage(
//		@PathVariable("id") Long id,
//		@RequestParam("img1") MultipartFile img1,
//		@RequestParam("img2") Optional<MultipartFile> img2,
//		@RequestParam("img3") Optional<MultipartFile> img3) throws IOException {
//		Hosting hosting = propertyService.getPropertyById(id);
//		String uploadDir = "src/main/resources/static/images/property/" + id;
//		Files.createDirectories(Paths.get(uploadDir));
//
//
//		Stream<Path> files1 = Files.walk(Paths.get(uploadDir));
//		FileUploadUtil.saveFile(uploadDir, files1.count() + ".jpg", img1);
//		Stream<Path> files2 = Files.walk(Paths.get(uploadDir));
//		img2.ifPresent(img -> FileUploadUtil.saveFile(uploadDir, files2.count() + ".jpg", img));
//		Stream<Path> files3 = Files.walk(Paths.get(uploadDir));
//		img3.ifPresent(img -> FileUploadUtil.saveFile(uploadDir, files3.count() + ".jpg", img));
//
//		files1.close();
//		files2.close();
//		files3.close();
//
//		hosting.setImageQuantity((int) Files.walk(Paths.get(uploadDir)).count() - 1);
//		propertyService.updateProperty(hosting);
//
//		return new ResponseEntity<>(hosting, HttpStatus.OK);
//	}

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

}
