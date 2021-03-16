package com.example.payment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.payment.model.Acquisition;
import com.example.payment.interfaces.service.IAcquisitionService;
import com.example.payment.repository.AcquisitionRepository;

@Service
public class AcquisitionServiceImp implements IAcquisitionService {
	
	private AcquisitionRepository acquisitionRepository;

	
	@Autowired
	public AcquisitionServiceImp(AcquisitionRepository acquisitionRepository) {
		this.acquisitionRepository = acquisitionRepository;
	}

	@Override
	@Transactional
	public void saveAcquisition(Acquisition acquisition) {
		acquisitionRepository.save(acquisition);
	}

	@Override
	public Acquisition findAcquisitionById(Long id) {
		return acquisitionRepository.findAcquisitionById(id);
	}

	@Override
	public List<Acquisition> findAllAcquisitions() {
		return acquisitionRepository.findAll();
	}

	@Override
	public void deleteAcquisition(Long id) {
		acquisitionRepository.deleteById(id);
	}

	@Override
	public Acquisition updateAcquisition(Acquisition acquisition) {
		return acquisitionRepository.save(acquisition);
	}

}
