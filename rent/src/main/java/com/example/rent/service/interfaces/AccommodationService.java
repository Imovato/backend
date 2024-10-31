package com.example.rent.service.interfaces;

import com.example.rent.entities.Accommodation;

import java.util.Optional;

public interface AccommodationService {
    Accommodation save(Accommodation property);
    Optional<Accommodation> findAccommodationById(Long id);
    void updateProperty(Accommodation property);
}
