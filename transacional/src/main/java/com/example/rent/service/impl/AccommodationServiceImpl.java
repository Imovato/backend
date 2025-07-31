package com.example.rent.service.impl;

import com.example.rent.entities.Accommodation;
import com.example.rent.repository.AccommodationRepository;
import com.example.rent.service.AccommodationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;

    @Override
    public Optional<Accommodation> findAccommodationById(String id) {
        return accommodationRepository.findById(id);
    }


    @Override
    public void changeStatusForRented(Accommodation accommodation) {
        accommodationRepository.save(accommodation);
    }



}
