package com.unipampa.crud.service;

import com.unipampa.crud.dto.AccommodationFilterDTO;
import com.unipampa.crud.entities.Accommodation;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface AccommodationService {

	void save(Accommodation hosting, MultipartFile[] images) throws IOException;

	Optional<Accommodation> findById(String id);

	List<Accommodation> findAll();

	List<Accommodation> findByFilters(AccommodationFilterDTO filters);

	void delete(String id);

	boolean existsByCodAddressAndNumber(String codeAddress, int number);

	void validateAuthorizationUser(Optional<Accommodation> accommodation);

}
