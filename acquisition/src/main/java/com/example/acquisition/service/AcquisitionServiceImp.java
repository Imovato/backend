package com.example.acquisition.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.acquisition.interfaces.services.IAcquisitionService;
import com.example.acquisition.model.Acquisition;
import com.example.acquisition.repository.AcquisitionRepository;

@Service
public class AcquisitionServiceImp implements IAcquisitionService{
	
	private AcquisitionRepository acquisitionRepository;
	
	@Autowired
	public AcquisitionServiceImp(AcquisitionRepository repository) {
		this.acquisitionRepository = repository;
	}

	@Override
	public void saveAcquisition(Acquisition acquisition) {
		acquisitionRepository.save(acquisition);
	}
		
	
}
