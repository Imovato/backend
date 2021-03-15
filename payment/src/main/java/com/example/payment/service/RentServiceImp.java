package com.example.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.payment.interfaces.service.IRentService;
import com.example.payment.model.Rent;
import com.example.payment.repository.RentRepository;

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
		
	
}
