package com.example.rent.service;

import com.example.rent.interfaces.services.IRentService;
import com.example.rent.model.Rent;
import com.example.rent.model.User;
import com.example.rent.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class RentServiceImp implements IRentService {

    @Autowired
    private RentRepository rentRepository;

    @Override
    public void saveRent(Rent rent) {
        rentRepository.save(rent);
    }

    @Override
    public Rent updateRent(Rent rent) {
        return rentRepository.save(rent);
    }

    @Override
    public Rent getRentById(UUID id) {
        return rentRepository.findRentById(id);
    }

    @Override
    public List<Rent> findAllRentsByUser(User user) {
        return rentRepository.findAllAcquisitionsByUser(user);
    }
}
