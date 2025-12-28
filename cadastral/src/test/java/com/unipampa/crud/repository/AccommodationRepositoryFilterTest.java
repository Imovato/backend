package com.unipampa.crud.repository;

import com.unipampa.crud.dto.AccommodationFilterDTO;
import com.unipampa.crud.entities.Accommodation;
import com.unipampa.crud.enums.AccommodationType;
import com.unipampa.crud.enums.AccommodationStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@ActiveProfiles("test")
@DisplayName("Testes do AccommodationRepository com Filtros")
class AccommodationRepositoryFilterTest {

	@Autowired
	private AccommodationRepository repository;

	private Accommodation accommodation1;
	private Accommodation accommodation2;
	private Accommodation accommodation3;

	@BeforeEach
	void setUp() {
		repository.deleteAll();

		// Acomodação 1: Apartamento em Porto Alegre
		accommodation1 = Accommodation.builder()
				.title("Apartamento Moderno")
				.description("Apartamento bem iluminado")
				.city("Porto Alegre")
				.state("RS")
				.neighborhood("Centro")
				.price(new BigDecimal("2500.00"))
				.type(AccommodationType.APARTMENT)
				.maxOccupancy(4)
				.roomCount(3)
				.bathroomCount(1)
				.allowsPets(true)
				.allowsChildren(true)
				.isSharedHosting(false)
				.stats(AccommodationStats.AVAILABLE)
				.hostId("host1")
				.build();

		// Acomodação 2: Casa em Viamão
		accommodation2 = Accommodation.builder()
				.title("Casa Confortável")
				.description("Casa com jardim")
				.city("Viamão")
				.state("RS")
				.neighborhood("Bonsucesso")
				.price(new BigDecimal("1500.00"))
				.type(AccommodationType.HOUSE)
				.maxOccupancy(6)
				.roomCount(4)
				.bathroomCount(2)
				.allowsPets(false)
				.allowsChildren(true)
				.isSharedHosting(false)
				.stats(AccommodationStats.AVAILABLE)
				.hostId("host2")
				.build();

		// Acomodação 3: Studio em Porto Alegre
		accommodation3 = Accommodation.builder()
				.title("Studio Aconchegante")
				.description("Studio com vista para o parque")
				.city("Porto Alegre")
				.state("RS")
				.neighborhood("Moinhos")
				.price(new BigDecimal("800.00"))
				.type(AccommodationType.APARTMENT)
				.maxOccupancy(2)
				.roomCount(1)
				.bathroomCount(1)
				.allowsPets(true)
				.allowsChildren(false)
				.isSharedHosting(true)
				.stats(AccommodationStats.AVAILABLE)
				.hostId("host3")
				.build();

		repository.save(accommodation1);
		repository.save(accommodation2);
		repository.save(accommodation3);
	}

	@Test
	@DisplayName("Deve retornar todas as acomodações quando nenhum filtro é fornecido")
	void testFindByFiltersNoFilters() {
		AccommodationFilterDTO filters = new AccommodationFilterDTO(null, null, null, null, null, null, null, null, null, null);
		List<Accommodation> result = repository.findByFilters(filters);

		assertEquals(3, result.size());
	}

	@Test
	@DisplayName("Deve filtrar por cidade")
	void testFindByFiltersCity() {
		AccommodationFilterDTO filters = new AccommodationFilterDTO("Porto Alegre", null, null, null, null, null, null, null, null, null);
		List<Accommodation> result = repository.findByFilters(filters);

		assertEquals(2, result.size());
		assertTrue(result.stream().allMatch(a -> a.getCity().equals("Porto Alegre")));
	}

	@Test
	@DisplayName("Deve filtrar por preço mínimo")
	void testFindByFiltersPriceMin() {
		AccommodationFilterDTO filters = new AccommodationFilterDTO(null, null, null, new BigDecimal("1500.00"), null, null, null, null, null, null);
		List<Accommodation> result = repository.findByFilters(filters);

		assertEquals(2, result.size());
		assertTrue(result.stream().allMatch(a -> a.getPrice().compareTo(new BigDecimal("1500.00")) >= 0));
	}

	@Test
	@DisplayName("Deve filtrar por preço máximo")
	void testFindByFiltersPriceMax() {
		AccommodationFilterDTO filters = new AccommodationFilterDTO(null, null, null, null, new BigDecimal("1500.00"), null, null, null, null, null);
		List<Accommodation> result = repository.findByFilters(filters);

		assertEquals(2, result.size());
		assertTrue(result.stream().allMatch(a -> a.getPrice().compareTo(new BigDecimal("1500.00")) <= 0));
	}

	@Test
	@DisplayName("Deve filtrar por tipo de acomodação")
	void testFindByFiltersType() {
		AccommodationFilterDTO filters = new AccommodationFilterDTO(null, null, null, null, null, AccommodationType.APARTMENT, null, null, null, null);
		List<Accommodation> result = repository.findByFilters(filters);

		assertEquals(1, result.size());
		assertEquals(AccommodationType.APARTMENT, result.get(0).getType());
	}

	@Test
	@DisplayName("Deve filtrar por permite pets")
	void testFindByFiltersAllowsPets() {
		AccommodationFilterDTO filters = new AccommodationFilterDTO(null, null, null, null, null, null, null, true, null, null);
		List<Accommodation> result = repository.findByFilters(filters);

		assertEquals(2, result.size());
		assertTrue(result.stream().allMatch(Accommodation::isAllowsPets));
	}

	@Test
	@DisplayName("Deve filtrar por permite crianças")
	void testFindByFiltersAllowsChildren() {
		AccommodationFilterDTO filters = new AccommodationFilterDTO(null, null, null, null, null, null, null, null, true, null);
		List<Accommodation> result = repository.findByFilters(filters);

		assertEquals(2, result.size());
		assertTrue(result.stream().allMatch(Accommodation::isAllowsChildren));
	}

	@Test
	@DisplayName("Deve filtrar por ocupância máxima mínima")
	void testFindByFiltersMaxOccupancy() {
		AccommodationFilterDTO filters = new AccommodationFilterDTO(null, null, null, null, null, null, 4, null, null, null);
		List<Accommodation> result = repository.findByFilters(filters);

		assertEquals(2, result.size());
		assertTrue(result.stream().allMatch(a -> a.getMaxOccupancy() >= 4));
	}

	@Test
	@DisplayName("Deve filtrar por alojamento compartilhado")
	void testFindByFiltersSharedHosting() {
		AccommodationFilterDTO filters = new AccommodationFilterDTO(null, null, null, null, null, null, null, null, null, true);
		List<Accommodation> result = repository.findByFilters(filters);

		assertEquals(1, result.size());
		assertTrue(result.get(0).isSharedHosting());
	}

	@Test
	@DisplayName("Deve aplicar múltiplos filtros simultaneamente (AND lógico)")
	void testFindByFiltersMultipleFilters() {
		AccommodationFilterDTO filters = new AccommodationFilterDTO(
				"Porto Alegre",    // city
				null,              // state
				null,              // neighborhood
				null,              // priceMin
				new BigDecimal("2000.00"), // priceMax
				null,              // accommodationType
				null,              // maxOccupancyMin
				true,              // allowsPets
				null,              // allowsChildren
				null               // isSharedHosting
		);
		List<Accommodation> result = repository.findByFilters(filters);

		assertEquals(1, result.size());
		assertEquals(accommodation3, result.get(0));
	}

	@Test
	@DisplayName("Deve retornar lista vazia quando nenhuma acomodação corresponde aos filtros")
	void testFindByFiltersNoMatches() {
		AccommodationFilterDTO filters = new AccommodationFilterDTO(
				"São Paulo",       // city inexistente
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null
		);
		List<Accommodation> result = repository.findByFilters(filters);

		assertTrue(result.isEmpty());
	}

	@Test
	@DisplayName("Deve filtrar por faixa de preço (min e max)")
	void testFindByFiltersPriceRange() {
		AccommodationFilterDTO filters = new AccommodationFilterDTO(
				null,
				null,
				null,
				new BigDecimal("1000.00"), // priceMin
				new BigDecimal("2600.00"), // priceMax
				null,
				null,
				null,
				null,
				null
		);
		List<Accommodation> result = repository.findByFilters(filters);

		assertEquals(2, result.size());
		assertTrue(result.stream().allMatch(a ->
				a.getPrice().compareTo(new BigDecimal("1000.00")) >= 0 &&
				a.getPrice().compareTo(new BigDecimal("2600.00")) <= 0
		));
	}

}

