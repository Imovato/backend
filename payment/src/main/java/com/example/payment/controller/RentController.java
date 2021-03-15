package com.example.payment.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payment.dto.RentDTO;
import com.example.payment.interfaces.service.IRentService;
import com.example.payment.model.Rent;

@RestController
@RequestMapping("/rent")
//@Api(value = "MICROSERVICE Payment")
public class RentController {
	
	private IRentService rentService;
	
	public RentController (IRentService service) {
		this.rentService = service;
	}
	
	@PostMapping("/save")
	public void saveRent(@RequestBody RentDTO dto) {
		Rent rent = new Rent();
		rent.setData(dto.getData());
		rent.setPropertie(dto.getPropertie());
		rent.setValue(dto.getAmountValue());
		rentService.saveRent(rent);
	}
	
	

}
