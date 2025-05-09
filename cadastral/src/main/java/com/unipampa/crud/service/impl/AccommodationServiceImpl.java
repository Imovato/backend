package com.unipampa.crud.service.impl;

import com.unipampa.crud.entities.Accommodation;
import com.unipampa.crud.repository.AccommodationRepository;
import com.unipampa.crud.sender.AccommodationSender;
import com.unipampa.crud.service.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {

	private AccommodationRepository propertyRepository;
	private AccommodationSender accommodationSender;

	@Autowired
	public AccommodationServiceImpl(AccommodationRepository repository, AccommodationSender sendMessage) {
		this.propertyRepository = repository;
		this.accommodationSender = sendMessage;
	}

	@Override
	@Transactional
	public void save(Accommodation hosting) {
		Accommodation hostingSaved = propertyRepository.save(hosting);
		accommodationSender.sendMessage(hostingSaved);
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
	public boolean existsByCodAddressAndNumber(String codeAddress, int number) {
		return propertyRepository.existsByZipCodeAndNumber(codeAddress, number);
	}

	@Override
	public Optional<Accommodation> findById(String id) {
		return propertyRepository.findById(id);
	}

}
