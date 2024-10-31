package com.example.rent.service.interfaces;

import com.example.rent.dto.RentDto;
import com.example.rent.dto.RentDtoUpdate;
import com.example.rent.entities.Rent;

import java.util.List;

public interface IRentService {
	
	Rent save(RentDto rentDto);
	void update(RentDtoUpdate rentDtoUpdate);
	void delete(Long id);
	Rent findById(Long id);
	List<Rent> listAll();
//	List<Rent> findRentsByCustomer_Id(Long id);
}
