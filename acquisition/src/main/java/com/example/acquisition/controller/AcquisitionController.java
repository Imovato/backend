package com.example.acquisition.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.acquisition.dto.AcquisitionDTO;
import com.example.acquisition.interfaces.services.IAcquisitionService;
import com.example.acquisition.interfaces.services.IPropertyService;
import com.example.acquisition.interfaces.services.IUserService;
import com.example.acquisition.model.Acquisition;
import com.example.acquisition.model.Property;
import com.example.acquisition.model.User;

@RestController
@RequestMapping("/acquisition")
public class AcquisitionController {
	
	private IAcquisitionService acquisitionService;
	private IPropertyService propertyService;
	private IUserService userService;
	
	public AcquisitionController (IAcquisitionService service, IPropertyService propertyService, IUserService userService) {
		this.acquisitionService = service;
		this.propertyService = propertyService;
		this.userService = userService;
	}
	
	@PostMapping("/save")
	public void saveAcquisition(@RequestBody AcquisitionDTO dto) {
		Property property = propertyService.findPropertyById(dto.getIdProperty());
		User user = userService.findUserById(dto.getIdUser());
		System.out.println(user);
		Acquisition acquisition = new Acquisition();
		acquisition.setData(dto.getData());
		acquisition.setProperty(property);
		acquisition.setUser(user);
		acquisition.setValue(dto.getValue());
		acquisition.setAmount(dto.getAmount());
		acquisitionService.saveAcquisition(acquisition); 
	}

}
