package com.example.payment.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payment.dto.AcquisitionDTO;
import com.example.payment.interfaces.service.IAcquisitionService;
import com.example.payment.model.Acquisition;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/acquistion")
@Api(value = "MICROSERVICE Payment")
public class AcquisitionController {

	private IAcquisitionService acquisitionService;

	public AcquisitionController(IAcquisitionService service) {
		this.acquisitionService = service;
	}

	@PostMapping("/save")
	@ApiOperation(value = "Salva uma aquisição")
	public ResponseEntity<?> saveAcquisition(@RequestBody AcquisitionDTO dto) {
		AcquisitionDTO dtoReturn = acquisitionService.createAcquisition(dto);
		acquisitionService.saveAcquisition(Acquisition.createAcquisition(dto));
		return new ResponseEntity<>(dtoReturn, HttpStatus.OK);
	}

	// Find all acquisitions
	@GetMapping("/all")
	@ApiOperation(value = "Lista com todas as aquisições")
	public ResponseEntity<?> getAllAcquisitions() {
		List<Acquisition> acquisitions = acquisitionService.findAllAcquisitions();
		return new ResponseEntity<>(acquisitions, HttpStatus.OK);
	}

	// Get an acquisition
	@GetMapping("/acquisition/find/{id}")
	@ApiOperation(value = "Pega uma aquisição pelo seu id")
	public ResponseEntity<?> getAcquisitionById(@PathVariable("id") Long id) {
		Acquisition acquisition = acquisitionService.findAcquisitionById(id);
		return new ResponseEntity<>(acquisition, HttpStatus.OK);
	}

	// Put acquisition
	@PutMapping("/acquisition/update")
	@ApiOperation(value = "Atualiza uma aquisição")
	public ResponseEntity<?> updateAcquisition(@RequestBody Acquisition acquisition) {
		Acquisition updateAcquisition = acquisitionService.updateAcquisition(acquisition);
		return new ResponseEntity<>(updateAcquisition, HttpStatus.OK);
	}

}
