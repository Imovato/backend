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
        return objectMapper.convertValue(requestDTO, Accommodation.class);
    }

    public AccommodationDTO toDTO(Accommodation entity) {
        String urlBase = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        List<String> imageUrls = entity.getImagesUrls().stream()
                .map(path -> {
                    Path p = Paths.get(path);
                    String id = p.getParent().getFileName().toString(); // exemplo: "6804eaef24e9aa24141421a1"
                    String filename = p.getFileName().toString();
                    return urlBase + "/accommodations/images/" + id + "/" + filename;
                })
                .collect(Collectors.toList());

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
                entity.getImagesUrls() != null ? entity.getImagesUrls().size() : 0,
                entity.getType(),
                entity.getMaxOccupancy(),
                imageUrls
        );


    }
}
