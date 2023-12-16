package com.unipampa.crud.service.impl;

import com.unipampa.crud.model.Accommodation;
import com.unipampa.crud.repository.AccommodationRepository;
import com.unipampa.crud.sender.PropertySender;
import com.unipampa.crud.service.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {

	private AccommodationRepository propertyRepository;
	private PropertySender propertySender;

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
	public void delete(String id) {
		propertyRepository.deleteById(id);

	}

	@Override
	public Optional<Accommodation> findById(String id) {
		return propertyRepository.findById(id);
	}

}
