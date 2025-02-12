package com.example.rent.service.interfaces;

import com.example.rent.dto.RentDto;
import com.example.rent.dto.RentDtoUpdate;
import com.example.rent.entities.Rent;
import com.example.rent.response.RentResponse;

import java.util.List;

public interface IRentService {
	
	Rent createNewRent(RentDto rentDto);

	List<RentResponse> findByUserId(Long id);
}
