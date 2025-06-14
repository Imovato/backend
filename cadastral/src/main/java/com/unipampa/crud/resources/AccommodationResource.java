package com.unipampa.crud.resources;

import com.unipampa.crud.config.security.SecurityUtil;
import com.unipampa.crud.dto.AccommodationDTO;
import com.unipampa.crud.dto.AccommodationRequestDTO;
import com.unipampa.crud.dto.ErrorResponse;
import com.unipampa.crud.entities.Accommodation;
import com.unipampa.crud.mappers.AccommodationMapper;
import com.unipampa.crud.service.AccommodationService;
import com.unipampa.crud.validations.ValidationsRegisterAccommodation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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

    private static final String ACOMODACAO_NAO_ENCONTRADA_PARA_EXCLUSAO = "Acomodação não encontrada para o ID fornecido. Exclusão não realizada.";
    private static final String ACOMODACAO_NAO_ECONTRADA_PARA_ATUALIZACAO = "Acomodação não encontrada para o ID fornecido. Atualização não realizada.";
    private static final String ERRO_AO_ATUALIZAR_ACOMODACAO = "Erro ao atualizar a acomodação. Verifique os dados fornecidos.";
    @Autowired
    private AccommodationService accommodationService;

    @Autowired
    private AccommodationMapper accommodationMapper;

    @Autowired
    private List<ValidationsRegisterAccommodation> validations;

    @PostMapping
    @Operation(summary = "Criar uma nova acomodação",
            description = "Valida e salva uma nova acomodação no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Acomodação criada com sucesso",
                    content = @Content(schema = @Schema(implementation = AccommodationDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<AccommodationDTO> save(@RequestBody AccommodationRequestDTO accommodationDTO) {

        validations.forEach(e -> e.validate(accommodationDTO));

        var accommodation = accommodationMapper.toEntity(accommodationDTO);

        String authenticatedUserId = SecurityUtil.getAuthenticatedUserId();
        accommodation.setHostId(authenticatedUserId);

        accommodationService.save(accommodation);

        URI location = URI.create("/accommodations/" + accommodation.getId());

        var accomodationResponseDTO = accommodationMapper.toDTO(accommodation);

        return ResponseEntity.created(location).body(accomodationResponseDTO);
    }

    @GetMapping
    @Operation(summary = "Listar todas as acomodações",
            description = "Retorna uma lista contendo todas as acomodações cadastradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
                    content = @Content(schema = @Schema(implementation = AccommodationDTO.class)))
    })
    public ResponseEntity<List<AccommodationDTO>> findAll() {
        List<AccommodationDTO> accommodationDtos = accommodationService.findAll()
                .stream()
                .map(accommodation -> accommodationMapper.toDTO(accommodation))
                .collect(Collectors.toList());

        return ResponseEntity.ok(accommodationDtos);
    }

    @GetMapping("{id}")
    @Operation(summary = "Buscar acomodação por ID",
            description = "Retorna os dados de uma acomodação específica a partir do seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Acomodação encontrada",
                    content = @Content(schema = @Schema(implementation = AccommodationDTO.class))),
            @ApiResponse(responseCode = "404", description = "Acomodação não encontrada",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Object> getById(@PathVariable("id") String id) {
        Optional<Accommodation> accommodationOptional = accommodationService.findById(id);

        if (accommodationOptional.isPresent()) {
            AccommodationDTO dto = accommodationMapper.toDTO(accommodationOptional.get());
            return ResponseEntity.ok(dto);
        }

        ErrorResponse errorResponse = new ErrorResponse("Acomodação não encontrada para o ID fornecido.", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Deletar acomodação por ID",
            description = "Remove uma acomodação existente a partir do seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Acomodação deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Acomodação não encontrada",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Object> deleteAccommodation(@PathVariable("id") String id) {
        var accommodation = accommodationService.findById(id);
        if (accommodation.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(ACOMODACAO_NAO_ENCONTRADA_PARA_EXCLUSAO, LocalDateTime.now()));
        }
        accommodationService.validateAuthorizationUser(accommodation);
        accommodationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    @Operation(summary = "Atualizar acomodação por ID",
            description = "Atualiza os dados de uma acomodação existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Acomodação atualizada com sucesso",
                    content = @Content(schema = @Schema(implementation = AccommodationDTO.class))),
            @ApiResponse(responseCode = "404", description = "Acomodação não encontrada",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Object> updateAccommodation(
            @PathVariable("id") String id,
            @RequestBody AccommodationRequestDTO accommodationDTO) {
        var existingAccommodation = accommodationService.findById(id);
        if (existingAccommodation.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(ACOMODACAO_NAO_ECONTRADA_PARA_ATUALIZACAO, LocalDateTime.now()));
        }
        accommodationService.validateAuthorizationUser(existingAccommodation);

        try {
            validations.forEach(e -> e.validate(accommodationDTO));

            Accommodation accommodation = existingAccommodation.get();
            accommodation.setTitle(accommodationDTO.title());
            accommodation.setAddress(accommodationDTO.address());
            accommodation.setPrice(accommodationDTO.price());

            accommodationService.save(accommodation);

            AccommodationDTO updatedDTO = accommodationMapper.toDTO(accommodation);
            return ResponseEntity.ok(updatedDTO);

        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(ERRO_AO_ATUALIZAR_ACOMODACAO, LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

}
