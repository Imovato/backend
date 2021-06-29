package com.example.acquisition.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

import java.util.List;

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

	public AcquisitionController(IAcquisitionService service, IPropertyService propertyService,
			IUserService userService) {
		this.acquisitionService = service;
		this.propertyService = propertyService;
		this.userService = userService;
	}

	@PostMapping("/save")
	public ResponseEntity<?> saveAcquisition(@RequestBody AcquisitionDTO dto) {
		Property property = propertyService.findPropertyById(dto.getIdProperty());
		User user = userService.findUserById(dto.getIdUser());
		AcquisitionDTO dtoReturn = acquisitionService.createAcquisition(dto);
		acquisitionService.saveAcquisition(Acquisition.createAcquisition(dto));
		return new ResponseEntity<>(dtoReturn, HttpStatus.OK);
	}

	@GetMapping("/user/find/{id}")
	@ApiOperation(value = "Encontra acquisitions através do id de um usuário")
	public ResponseEntity<?> getAcquisitionsByUserId(@PathVariable("id") Long id) {
		User user = userService.findUserById(id);
		List<Acquisition> acquisitions = acquisitionService.findAllAcquisitionsByUser(user);
		return new ResponseEntity<>(acquisitions, HttpStatus.OK);
	}

	@GetMapping("/property/find/{id}")
	@ApiOperation(value = "Encontra acquisitions através do id de um imóvel")
	public ResponseEntity<?> getAcquisitionsByPropertyId(@PathVariable("id") Long id) {
		Property property = propertyService.findPropertyById(id);
		Acquisition acquisition = acquisitionService.findAcquisitionByProperty(property);
		return new ResponseEntity<>(acquisition, HttpStatus.OK);
	}

}
