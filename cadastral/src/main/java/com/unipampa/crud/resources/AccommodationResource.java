package com.unipampa.crud.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unipampa.crud.dto.AccommodationDTO;
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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
	@Operation(summary = "Salva uma acomodação)")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Recurso salvo com sucesso!" )
	})
	public ResponseEntity<Accommodation> save(@RequestBody AccommodationDTO accommodationDTO) {

		validations.forEach(e -> e.validate(accommodationDTO));

		var accommodation = mapper.convertValue(accommodationDTO, Accommodation.class);
		accommodationService.save(accommodation);
		return ResponseEntity.status(HttpStatus.OK).body(accommodation);
	}
	
	@GetMapping
	@Operation(summary = "Retorna uma lista com todas as acomodações")
	public ResponseEntity<List<Accommodation>> findAll() {
		List<Accommodation> accommodations = accommodationService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(accommodations);
	}

	@GetMapping("{id}")
	@Operation(summary = "Encontra uma acomodação através do id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Recurso encontrado com sucesso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")
	})
	public ResponseEntity<Object> getById(@PathVariable("id") String id) {
		Optional<Accommodation> accomodation = accommodationService.findById(id);
		return accomodation.<ResponseEntity<Object>>map(accommodation -> new ResponseEntity<>(accommodation, HttpStatus.OK))
				.orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Acomodação não encontrada para esse id"));
	}
	
	@DeleteMapping("{id}")
	@Operation(summary = "Deleta uma acomodação através do id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Recurso deletado com sucesso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")
	})
	public ResponseEntity<Object> deleteAccommodation(@PathVariable("id") String id) {
		Optional<Accommodation> accommodation = accommodationService.findById(id);
		if(accommodation.isEmpty()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Acomodação não encontrada para esse id, portanto não pode ser deletada!");
		}
		accommodationService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
			@RequestBody AccommodationDTO accommodationDTO) {

		Optional<Accommodation> existingAccommodation = accommodationService.findById(id);
		if (existingAccommodation.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Acomodação não encontrada para esse id, portanto não pode ser atualizada!");
		}

		try {
			validations.forEach(e -> e.validate(accommodationDTO));

			Accommodation accommodation = existingAccommodation.get();

			accommodation.setTitle(accommodationDTO.title());
			accommodation.setAddress(accommodationDTO.adress());
			accommodation.setPrice(accommodationDTO.price());

			accommodationService.save(accommodation);

			return ResponseEntity.ok(accommodation);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar a acomodação. Verifique os dados fornecidos.");
		}
	}

	@GetMapping("{id}/price-per-person")
	@Operation(summary = "Shows the price each person will pay for the shared accommodation")
	public ResponseEntity<Object> getPricePerPerson (
			@PathVariable("id") String id,
			@RequestParam(value = "people", required = false) Integer numberOfPeople
	) {
		Optional<Accommodation> accommodationOpt = accommodationService.findById(id);

		if (accommodationOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Accomodations not found for this ID!");
		}

        Accommodation accommodation = accommodationOpt.get();
        int maxOccupancy = accommodation.getMaxOccupancy();

        if (maxOccupancy <= 0) {
            return ResponseEntity.badRequest().body("Max occupancy must be greater than 0");
        }

		if (numberOfPeople == null) {
			numberOfPeople = maxOccupancy;
		}

        if (numberOfPeople <= 0) {
            return ResponseEntity.badRequest().body("Number of people must be greater than 0");
        }

        if (numberOfPeople > maxOccupancy) {
            return ResponseEntity.badRequest().body("Number of people exceeds the maximum occupancy");
        }

        BigDecimal totalPrice = accommodation.getPrice();

        BigDecimal pricePerPerson = totalPrice.divide(BigDecimal.valueOf(numberOfPeople), 2, RoundingMode.HALF_UP);

        Map<String, Object> response = new HashMap<>();
        response.put("totalPrice", totalPrice);
        response.put("numberOfPeople", numberOfPeople);
        response.put("pricePerPerson", pricePerPerson);
        response.put("maxOccupancy", maxOccupancy);

        return ResponseEntity.ok(response);
    }
}
