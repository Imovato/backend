package com.example.rent.interfaces.services;

import com.example.rent.model.Rent;
import com.example.rent.model.User;
import java.util.List;
import java.util.UUID;

public interface IRentService {
	
	void saveRent(Rent rent);
	public Rent updateRent(Rent rent);
	public Rent getRentById(UUID id);
	public List<Rent> findAllRentsByUser(User user);

}
