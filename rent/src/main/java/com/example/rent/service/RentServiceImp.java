package com.example.rent.service;

import com.example.rent.interfaces.services.IRentServices;
import com.example.rent.model.Rent;
import com.example.rent.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentServiceImp implements IRentServices {

    @Autowired
    private RentRepository rentRepository;

    @Override
    public void saveRent(Rent rent) {
        rentRepository.save(rent);
    }

    @Override
    public Rent updateRent(Rent rent) {
        return null;
    }

    @Override
    public Rent getRentById(Long id) {
        return rentRepository.findRentById(id);
    }
}
