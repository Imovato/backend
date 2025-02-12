package com.example.rent.service;

import com.example.rent.entities.Accommodation;

import java.util.Optional;

public interface AccommodationService {
    Optional<Accommodation> findAccommodationById(Long id);

    void changeStatusForRented(Accommodation accommodation);
}
