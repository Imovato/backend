package com.unipampa.crud.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unipampa.crud.dto.AccommodationDTO;
import com.unipampa.crud.dto.AccommodationRequestDTO;
import com.unipampa.crud.entities.Accommodation;
import org.springframework.stereotype.Component;

@Component
public class AccommodationMapper {

    private final ObjectMapper objectMapper;

    public AccommodationMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Accommodation toEntity(AccommodationDTO dto) {
        return objectMapper.convertValue(dto, Accommodation.class);
    }

    public Accommodation toEntity(AccommodationRequestDTO requestDTO) {
        return objectMapper.convertValue(requestDTO, Accommodation.class);
    }

    public AccommodationDTO toDTO(Accommodation entity) {
        return objectMapper.convertValue(entity, AccommodationDTO.class);
    }
}
