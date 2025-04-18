package com.example.rent.service;

import com.example.rent.response.RentResponse;

import java.util.List;

public interface RentService {


	RentResponse processCheckin(Long idReservation);

	List<RentResponse> findByUserId(Long id);
}
