package com.unipampa.crud.service.impl;

import java.util.List;
import java.util.Optional;

import com.unipampa.crud.service.StrategySaveProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unipampa.crud.service.AccommodationService;
import com.unipampa.crud.model.Accommodation;
import com.unipampa.crud.repository.AccommodationRepository;
import com.unipampa.crud.sender.PropertySender;

@Service
public class AccommodationServiceImpl implements AccommodationService {

	private AccommodationRepository propertyRepository;
	private PropertySender propertySender;

	private StrategySaveProperty strategySaveProperty;

	@Autowired
	public AccommodationServiceImpl(AccommodationRepository repository, PropertySender sendMessage) {
		this.propertyRepository = repository;
		this.propertySender = sendMessage;
	}

	@Override
	@Transactional
	public void save(Accommodation hosting) {
		Accommodation hostingSaved = propertyRepository.save(hosting);
		propertySender.sendMessage(hostingSaved);
	}

	@Override
	public List<Accommodation> findAll() {
		return propertyRepository.findAll();
	}


	@Override
	public void delete(Long id) {
		propertyRepository.deleteById(id);

	}

	@Override
	public Optional<Accommodation> findById(Long id) {
		return propertyRepository.findById(id);
	}



}
