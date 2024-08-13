package com.unipampa.crud.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unipampa.crud.dto.AccommodationDTO;
import com.unipampa.crud.model.Accommodation;
import com.unipampa.crud.service.AccommodationService;
import com.unipampa.crud.validations.ValidationsRegisterAccommodation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accommodations")
@Api(value = "API Crud Property")
public class AccommodationController {

	@Autowired
	private AccommodationService accommodationService;

	@Autowired
	ObjectMapper mapper;

	@Autowired
	List<ValidationsRegisterAccommodation> validations;

	@PostMapping("/add")
	@ApiOperation(value = "Salva uma acomodação)")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Recurso salvo com sucesso!" )
	})
	public ResponseEntity<Accommodation> save(@RequestBody AccommodationDTO accommodationDTO) {

		validations.forEach(e -> e.validate(accommodationDTO));

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
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Recurso encontrado com sucesso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado")
	})
	public ResponseEntity<Object> getById(@PathVariable("id") String id) {
		Optional<Accommodation> accomodation = accommodationService.findById(id);
		return accomodation.<ResponseEntity<Object>>map(accommodation -> new ResponseEntity<>(accommodation, HttpStatus.OK))
				.orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Acomodação não encontrada para esse id"));
	}
	
	@DeleteMapping("/delete/{id}")
	@ApiOperation(value = "Deleta uma acomodação através do id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Recurso deletado com sucesso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado")
	})
	public ResponseEntity<Object> deleteAccommodation(@PathVariable("id") String id) {
		Optional<Accommodation> accommodation = accommodationService.findById(id);
		if(accommodation.isEmpty()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Acomodação não encontrada para esse id, portanto não pode ser deletada!");
		}
		accommodationService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
