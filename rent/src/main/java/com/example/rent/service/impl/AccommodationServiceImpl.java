package com.example.rent.service.impl;

import com.example.rent.entities.Accommodation;
import com.example.rent.repository.AccommodationRepository;
import com.example.rent.service.interfaces.AccommodationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;

    @Override
    public Optional<Accommodation> findAccommodationById(Long id) {
        return accommodationRepository.findById(id);
    }

    @Override
    public void updateProperty(Accommodation property) {
         accommodationRepository.save(property);
    }

    @Override
    public Accommodation save(Accommodation property){
        return accommodationRepository.save(property);
    }

}
