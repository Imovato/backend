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
import com.example.acquisition.model.Acquisition;

@RestController
@RequestMapping("/acquisition")
public class AcquisitionController {
	
	private IAcquisitionService acquisitionService;
	
	public AcquisitionController (IAcquisitionService service) {
		this.acquisitionService = service;
	}

}
