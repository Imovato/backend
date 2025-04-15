package com.example.rent.service;

import com.example.rent.dto.RentDto;
import com.example.rent.response.RentResponse;

import java.util.List;

public interface RentService {

	RentResponse createNewRent(RentDto rentDto);

	List<RentResponse> findByUserId(Long id);
}
