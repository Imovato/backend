package com.example.rent.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rent.interfaces.services.IRentService;
import com.example.rent.model.Rent;
import com.example.rent.repository.RentRepository;

@Service
public class RentServiceImp implements IRentService{
	
	private RentRepository rentRepository;
	
	@Autowired
	public RentServiceImp(RentRepository repository) {
		this.rentRepository = repository;
	}

	@Override
	public void saveRent(Rent rent) {
		rentRepository.save(rent);
	}

	@Override
	public Rent updateRent(Rent rent) {
		return rentRepository.save(rent);
	}

	@Override
	public Rent findRentById(Long id) {
		return rentRepository.findRentById(id);
	}
		
	
}
