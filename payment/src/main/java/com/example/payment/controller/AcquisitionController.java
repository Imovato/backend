package com.example.payment.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/acquistion")
//@Api(value = "MICROSERVICE Payment")
public class AcquisitionController {
	
	private IAcquisitionService acquisitionService;
	
	public AcquisitionController (IAcquisitionService service) {
		this.acquisitionService = service;
	}
	
	@PostMapping("/save")
	@ApiOperation(value = "Adiciona uma acquisição")
	public void saveAcquisition(@RequestBody AcquisitionDTO dto) {
		Acquisition acquisition = new Acquisition();
		acquisition.setDate(dto.getDate());
		acquisition.setId(dto.getId());
		acquisition.setProperty(dto.getProperty());
		acquisition.setValue(dto.getValue());
		acquisitionService.saveAcquisition(acquisition);
	}
	
	//Find all acquisitions
	@GetMapping("/all")
	public ResponseEntity<?> getAllAcquisitions () {
        List<Acquisition> acquisitions = acquisitionService.findAllAcquisitions();
		return new ResponseEntity<>(acquisitions, HttpStatus.OK);
    }
	
	//Get an acquisition
	@GetMapping("/employee/find/{id}")
	public ResponseEntity<?> getAcquisitionById (@PathVariable("id") Long id) {
		Acquisition acquisition = acquisitionService.findAcquisitionById(id);
		return new ResponseEntity<>(acquisition, HttpStatus.OK);
    }
	//Put acquisition
    @PutMapping("/employee/update")
    public ResponseEntity<?> updateAcquisition(@RequestBody Acquisition acquisition) {
    	Acquisition updateAcquisition = acquisitionService.updateAcquisition(acquisition);
		return new ResponseEntity<>(updateAcquisition, HttpStatus.OK);
    }
    
	//Delete acquisition
    @DeleteMapping("/delete/{id}")
    public void deleteAcquisition(@PathVariable("id") Long id) {
    	acquisitionService.deleteAcquisition(id);
    }
	

}
