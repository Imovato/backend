package com.example.rent.service.interfaces;

import com.example.rent.model.Rent;
import com.example.rent.model.Customer;
import java.util.List;

public interface IRentService {
	
	void saveRent(Rent rent);
	Rent updateRent(Rent rent);
	Rent getRentById(Long id);
	List<Rent> findAllRentsByUser(Customer customer);

	String contractTime(Rent rent);
}
