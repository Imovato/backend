package com.example.acquisition.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.acquisition.interfaces.services.IAcquisitionService;
import com.example.acquisition.model.Acquisition;
import com.example.acquisition.model.Property;
import com.example.acquisition.model.User;
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

	@Override
	public List<Acquisition> findAllAcquisitionsByUser(User user) {
		return acquisitionRepository.findAllAcquisitionsByUser(user);
	}

	@Override
	public Acquisition findAcquisitionByProperty(Property property) {
		return acquisitionRepository.findAcquisitionByProperty(property);
	}
		
	
}
