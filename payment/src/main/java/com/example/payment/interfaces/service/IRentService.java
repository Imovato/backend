package com.example.payment.interfaces.service;

import com.example.payment.model.Rent;

public interface IRentService {
	
	void saveRent(Rent rent);
	public Rent updateRent(Rent rent);
	public Rent findRentById(Long id);

}
