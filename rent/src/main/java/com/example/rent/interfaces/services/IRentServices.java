package com.example.rent.interfaces.services;

import com.example.rent.model.Rent;

public interface IRentServices {
	
	void saveRent(Rent rent);
	public Rent updateRent(Rent rent);
	public Rent findRentById(Long id);

}
