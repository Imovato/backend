package com.unipampa.crud.controller;

import java.util.List;

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
	public Acquisition getAcquisitionById(@PathVariable("id") Long id) {
		return acquisitionService.findAcquisitionById(id);
	}
	
	//get all acquisitions
	@GetMapping("/all")
	public List<Acquisition> getAllAcquisitions () {
        List<Acquisition> acquisitions = acquisitionService.findAllAcquisitions();
        return acquisitions;
    }
	
	//delete acquisition by id
	@DeleteMapping("/delete/{id}")
	public void deleteAcquisition(@PathVariable("id") Long id) {
		acquisitionService.deleteAcquisition(id);
	}
	
	//update acquisition
	@PutMapping
	public Acquisition updateAcquisition(@RequestBody Acquisition acquisition) {
		return acquisitionService.updateAcquisition(acquisition);
	}
}
