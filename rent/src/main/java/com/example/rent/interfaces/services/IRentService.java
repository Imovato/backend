package com.example.rent.interfaces.services;

import com.example.rent.model.Rent;
import com.example.rent.model.Customer;
import java.util.List;

public interface IRentService {
	
	void saveRent(Rent rent);
	public Rent updateRent(Rent rent);
	public Rent getRentById(Long id);
	public List<Rent> findAllRentsByUser(Customer customer);

}
