package com.unipampa.crud.controller;

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

import com.unipampa.crud.dto.AcquisitionDTO;
import com.unipampa.crud.interfaces.service.IAcquisitionService;
import com.unipampa.crud.model.Acquisition;

@RestController
@RequestMapping("/api/acquisition")
public class AcquisitionController {
	
	private IAcquisitionService acquisitionService;
	
	public AcquisitionController(IAcquisitionService acquisitionService) {
		this.acquisitionService = acquisitionService;
	}

	
	//add new acquisition
	@PostMapping("/add")
	public void saveAcquisition(@RequestBody AcquisitionDTO acquisitionDTO) {
		Acquisition acquisition = new Acquisition();
		acquisition.setDate(acquisitionDTO.getDate());
		acquisition.setPrice(acquisitionDTO.getPrice());
		acquisition.setProperty_id(acquisition.getProperty_id());
	}
	
	//get acquisition by id
	@GetMapping("/find/{id}")
	public ResponseEntity<?> getAcquisitionById(@PathVariable("id") Long id) {
		Acquisition acquisition = acquisitionService.findAcquisitionById(id);
		return new ResponseEntity<>(acquisition, HttpStatus.OK);
	}
	
	//get all acquisitions
	@GetMapping("/all")
	public ResponseEntity<?> getAllAcquisitions () {
        List<Acquisition> acquisitions = acquisitionService.findAllAcquisitions();
        return new ResponseEntity<>(acquisitions, HttpStatus.OK);
    }
	
	//delete acquisition by id
	@DeleteMapping("/delete/{id}")
	public void deleteAcquisition(@PathVariable("id") Long id) {
		acquisitionService.deleteAcquisition(id);
	}
	
	//update acquisition
	@PutMapping
	public ResponseEntity<?> updateAcquisition(@RequestBody Acquisition acquisition) {
		Acquisition updateAcquisition = acquisitionService.updateAcquisition(acquisition);
		return new ResponseEntity<>(acquisition, HttpStatus.OK);
	}
}
