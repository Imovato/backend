package com.example.rent.service.interfaces;

import com.example.rent.model.Rent;
import com.example.rent.model.Customer;
import java.util.List;

public interface IRentService {
	
	void saveRent(Rent rent);
	public Rent updateRent(Rent rent);
	public Rent getRentById(Long id);
	public List<Rent> findAllRentsByUser(Customer customer);

	public boolean isCPF(Customer customer) ;

	public boolean isSalary(Customer customer);

	public boolean hasGuarantor(Customer customer);

	public String contractTime(Rent rent);


}
