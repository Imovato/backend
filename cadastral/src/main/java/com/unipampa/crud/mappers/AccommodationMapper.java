package com.unipampa.crud.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unipampa.crud.dto.AccommodationDTO;
import com.unipampa.crud.dto.AccommodationRequestDTO;
import com.unipampa.crud.entities.Accommodation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccommodationMapper {


    @Autowired
    private HttpServletRequest request;

    private final ObjectMapper objectMapper;

    public AccommodationMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Accommodation toEntity(AccommodationDTO dto) {
        return objectMapper.convertValue(dto, Accommodation.class);
    }

    public Accommodation toEntity(AccommodationRequestDTO requestDTO) {
        return Accommodation.builder()
                .title(requestDTO.title())
                .neighborhood(requestDTO.neighborhood())
                .zipCode(requestDTO.codAddress())
                .city(requestDTO.city())
                .description(requestDTO.description())
                .address(requestDTO.address())
                .state(requestDTO.state())
                .price(requestDTO.price())
                .streetNumber(requestDTO.streetNumber())
                .type(requestDTO.accommodationType()) // Mapeia accommodationType -> type
                .maxOccupancy(requestDTO.maxOccupancy())
                .roomCount(requestDTO.roomCount())
                .bathroomCount(requestDTO.bathroomCount())
                .allowsPets(requestDTO.allowsPets())
                .isSharedHosting(requestDTO.isSharedHosting())
                .imagesUrls(requestDTO.imagesUrls())
                .build();
    }

    public AccommodationDTO toDTO(Accommodation entity) {

        List<String> imageUrls = entity.getImagesUrls();

        return new AccommodationDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getNeighborhood(),
                entity.getZipCode(),
                entity.getCity(),
                entity.getDescription(),
                entity.getAddress(),
                entity.getState(),
                entity.getPrice(),
                entity.getStreetNumber(),
                imageUrls != null ? imageUrls.size() : 0,
                entity.getType(),
                entity.getMaxOccupancy(),
                entity.getRoomCount(),
                entity.getBathroomCount(),
                entity.isAllowsPets(),
                entity.isSharedHosting(),
                imageUrls
        );
    }

}
