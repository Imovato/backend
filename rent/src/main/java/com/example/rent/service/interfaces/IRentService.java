package com.example.rent.service.interfaces;

import com.example.rent.model.Rent;
import com.example.rent.model.Customer;
import java.util.List;

public interface IRentService {
	
	void save(Rent rent);
	Rent replace(Rent rent);
	Rent findByIdOrThrowBadRequestException(Long id);
	public void delete(Long id);
	public List<Rent> listAll();
	List<Rent> findAllRentsByUser(Customer customer);

	String contractTime(Rent rent);
}
