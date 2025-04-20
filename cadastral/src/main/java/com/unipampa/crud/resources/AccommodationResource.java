package com.unipampa.crud.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unipampa.crud.dto.AccommodationDTO;
import com.unipampa.crud.dto.ErrorResponse;
import com.unipampa.crud.entities.Accommodation;
import com.unipampa.crud.service.AccommodationService;
import com.unipampa.crud.validations.ValidationsRegisterAccommodation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/accommodations")
public class AccommodationResource {

	@Autowired
	private AccommodationService accommodationService;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private List<ValidationsRegisterAccommodation> validations;

	@PostMapping
	@Operation(summary = "Cria uma nova acomodação",
			description = "Valida e salva uma nova acomodação no sistema.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Acomodação criada com sucesso"),
			@ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
	})
	public ResponseEntity<Accommodation> save(@RequestBody AccommodationDTO accommodationDTO) {

		validations.forEach(e -> e.validate(accommodationDTO));

		var accommodation = mapper.convertValue(accommodationDTO, Accommodation.class);
		accommodationService.save(accommodation);

		URI location = URI.create("/accommodations/" + accommodation.getId());

		return ResponseEntity.created(location).body(accommodation);
	}

	@GetMapping
	@Operation(summary = "Retorna uma lista com todas as acomodações")
	public ResponseEntity<List<AccommodationDTO>> findAll() {
		List<AccommodationDTO> accommodationDtos = accommodationService.findAll()
				.stream()
				.map(accommodation -> mapper.convertValue(accommodation, AccommodationDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(accommodationDtos);
	}

	@GetMapping("{id}")
	@Operation(summary = "Encontra uma acomodação através do id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Recurso encontrado com sucesso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")
	})
	public ResponseEntity<Object> getById(@PathVariable("id") String id) {
		Optional<Accommodation> accommodation = accommodationService.findById(id);

		if (accommodation.isPresent()) {
			AccommodationDTO dto = mapper.convertValue(accommodation.get(), AccommodationDTO.class);
			return ResponseEntity.ok(dto);
		}

		ErrorResponse errorResponse = new ErrorResponse("Acomodação não encontrada para esse id", LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}


	@DeleteMapping("{id}")
	@Operation(summary = "Deleta uma Acomodação através do id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Acomodação deletada com sucesso"),
			@ApiResponse(responseCode = "404", description = "Acomodação não encontrada")
	})
	public ResponseEntity<Object> deleteAccommodation(@PathVariable("id") String id) {
		Optional<Accommodation> accommodation = accommodationService.findById(id);

		if (accommodation.isEmpty()) {
			ErrorResponse errorResponse = new ErrorResponse("Acomodação não encontrada para esse id, portanto não pode ser deletada!", LocalDateTime.now());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}

		accommodationService.delete(id);

		return ResponseEntity.noContent().build();
	}

	@PutMapping("{id}")
	@Operation(summary = "Atualiza uma acomodação através do id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Acomodação atualizada com sucesso"),
			@ApiResponse(responseCode = "404", description = "Acomodação não encontrada"),
			@ApiResponse(responseCode = "400", description = "Dados de acomodação inválidos")
	})
	public ResponseEntity<Object> updateAccommodation(
			@PathVariable("id") String id,
			@RequestBody AccommodationDTO accommodationDTO
	) {
		Optional<Accommodation> existingAccommodation = accommodationService.findById(id);
		if (existingAccommodation.isEmpty()) {
			ErrorResponse errorResponse = new ErrorResponse("Acomodação não encontrada para esse id, portanto não pode ser atualizada!", LocalDateTime.now());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}

		try {
			validations.forEach(e -> e.validate(accommodationDTO)); // Custom validation logic

			Accommodation accommodation = existingAccommodation.get();
			accommodation.setTitle(accommodationDTO.title());
			accommodation.setAddress(accommodationDTO.address());
			accommodation.setPrice(accommodationDTO.price());

			accommodationService.save(accommodation);

			AccommodationDTO updatedDTO = mapper.convertValue(accommodation, AccommodationDTO.class);

			return ResponseEntity.ok(updatedDTO);
		} catch (Exception e) {
			ErrorResponse errorResponse = new ErrorResponse("Erro ao atualizar a acomodação. Verifique os dados fornecidos.", LocalDateTime.now());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}

}
