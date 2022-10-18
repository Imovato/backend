package com.example.rent.service;

import com.example.rent.interfaces.services.IRentService;
import com.example.rent.model.Rent;
import com.example.rent.model.Customer;
import com.example.rent.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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
    public Rent getRentById(Long id) {
        return rentRepository.findRentById(id);
    }

    @Override
    public List<Rent> findAllRentsByUser(Customer customer) {
        return rentRepository.findAllAcquisitionsByCustomer(customer);
    }
}
