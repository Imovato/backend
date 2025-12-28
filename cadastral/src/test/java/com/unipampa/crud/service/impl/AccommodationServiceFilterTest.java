package com.unipampa.crud.service.impl;

import com.unipampa.crud.dto.AccommodationFilterDTO;
import com.unipampa.crud.entities.Accommodation;
import com.unipampa.crud.enums.AccommodationType;
import com.unipampa.crud.enums.AccommodationStats;
import com.unipampa.crud.repository.AccommodationRepository;
import com.unipampa.crud.sender.AccommodationSender;
import com.unipampa.crud.service.ImageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes do AccommodationService com Filtros")
class AccommodationServiceFilterTest {

	@Mock
	private AccommodationRepository accommodationRepository;

	@Mock
	private AccommodationSender accommodationSender;

	@Mock
	private ImageService imageService;

	@InjectMocks
	private AccommodationServiceImpl accommodationService;

	private Accommodation accommodation1;
	private Accommodation accommodation2;
	private Accommodation accommodation3;

	@BeforeEach
	void setUp() {
		// Acomodação 1
		accommodation1 = Accommodation.builder()
				.id("1")
				.title("Apartamento Moderno")
				.city("Porto Alegre")
				.state("RS")
				.neighborhood("Centro")
				.price(new BigDecimal("2500.00"))
				.type(AccommodationType.APARTMENT)
				.maxOccupancy(4)
				.allowsPets(true)
				.allowsChildren(true)
				.isSharedHosting(false)
				.stats(AccommodationStats.AVAILABLE)
				.hostId("host1")
				.build();

		// Acomodação 2
		accommodation2 = Accommodation.builder()
				.id("2")
				.title("Casa Confortável")
				.city("Viamão")
				.state("RS")
				.neighborhood("Bonsucesso")
				.price(new BigDecimal("1500.00"))
				.type(AccommodationType.HOUSE)
				.maxOccupancy(6)
				.allowsPets(false)
				.allowsChildren(true)
				.isSharedHosting(false)
				.stats(AccommodationStats.AVAILABLE)
				.hostId("host2")
				.build();

		// Acomodação 3
		accommodation3 = Accommodation.builder()
				.id("3")
				.title("Studio Aconchegante")
				.city("Porto Alegre")
				.state("RS")
				.neighborhood("Moinhos")
				.price(new BigDecimal("800.00"))
				.type(AccommodationType.APARTMENT)
				.maxOccupancy(2)
				.allowsPets(true)
				.allowsChildren(false)
				.isSharedHosting(true)
				.stats(AccommodationStats.AVAILABLE)
				.hostId("host3")
				.build();
	}

	@Test
	@DisplayName("Deve retornar acomodações filtradas por cidade")
	void testFindByFiltersCityFilter() {
		AccommodationFilterDTO filters = new AccommodationFilterDTO(
				"Porto Alegre", null, null, null, null, null, null, null, null, null
		);

		when(accommodationRepository.findByFilters(filters))
				.thenReturn(Arrays.asList(accommodation1, accommodation3));

		List<Accommodation> result = accommodationService.findByFilters(filters);

		assertEquals(2, result.size());
		assertTrue(result.stream().allMatch(a -> a.getCity().equals("Porto Alegre")));
	}

	@Test
	@DisplayName("Deve retornar acomodações filtradas por preço")
	void testFindByFiltersPriceFilter() {
		AccommodationFilterDTO filters = new AccommodationFilterDTO(
				null, null, null, new BigDecimal("1000.00"), new BigDecimal("2600.00"), null, null, null, null, null
		);

		when(accommodationRepository.findByFilters(filters))
				.thenReturn(Arrays.asList(accommodation2, accommodation1));

		List<Accommodation> result = accommodationService.findByFilters(filters);

		assertEquals(2, result.size());
		assertTrue(result.stream().allMatch(a ->
				a.getPrice().compareTo(new BigDecimal("1000.00")) >= 0 &&
				a.getPrice().compareTo(new BigDecimal("2600.00")) <= 0
		));
	}

	@Test
	@DisplayName("Deve retornar acomodações que permitem pets")
	void testFindByFiltersAllowsPetsFilter() {
		AccommodationFilterDTO filters = new AccommodationFilterDTO(
				null, null, null, null, null, null, null, true, null, null
		);

		when(accommodationRepository.findByFilters(filters))
				.thenReturn(Arrays.asList(accommodation1, accommodation3));

		List<Accommodation> result = accommodationService.findByFilters(filters);

		assertEquals(2, result.size());
		assertTrue(result.stream().allMatch(Accommodation::isAllowsPets));
	}

	@Test
	@DisplayName("Deve retornar acomodações por tipo")
	void testFindByFiltersTypeFilter() {
		AccommodationFilterDTO filters = new AccommodationFilterDTO(
				null, null, null, null, null, AccommodationType.HOUSE, null, null, null, null
		);

		when(accommodationRepository.findByFilters(filters))
				.thenReturn(Arrays.asList(accommodation2));

		List<Accommodation> result = accommodationService.findByFilters(filters);

		assertEquals(1, result.size());
		assertEquals(AccommodationType.HOUSE, result.get(0).getType());
	}

	@Test
	@DisplayName("Deve retornar lista vazia quando nenhuma acomodação corresponde aos filtros")
	void testFindByFiltersNoMatches() {
		AccommodationFilterDTO filters = new AccommodationFilterDTO(
				"São Paulo", null, null, null, null, null, null, null, null, null
		);

		when(accommodationRepository.findByFilters(filters))
				.thenReturn(Arrays.asList());

		List<Accommodation> result = accommodationService.findByFilters(filters);

		assertTrue(result.isEmpty());
	}

	@Test
	@DisplayName("Deve retornar todas as acomodações com múltiplos filtros")
	void testFindByFiltersMultipleFilters() {
		AccommodationFilterDTO filters = new AccommodationFilterDTO(
				"Porto Alegre",     // city
				null,               // state
				null,               // neighborhood
				null,               // priceMin
				new BigDecimal("2000.00"), // priceMax
				null,               // accommodationType
				null,               // maxOccupancyMin
				true,               // allowsPets
				null,               // allowsChildren
				null                // isSharedHosting
		);

		when(accommodationRepository.findByFilters(filters))
				.thenReturn(Arrays.asList(accommodation3));

		List<Accommodation> result = accommodationService.findByFilters(filters);

		assertEquals(1, result.size());
		assertEquals(accommodation3, result.get(0));
	}

}

