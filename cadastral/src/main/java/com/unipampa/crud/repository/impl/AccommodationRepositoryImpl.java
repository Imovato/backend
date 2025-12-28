package com.unipampa.crud.repository.impl;

import com.unipampa.crud.dto.AccommodationFilterDTO;
import com.unipampa.crud.entities.Accommodation;
import com.unipampa.crud.repository.AccommodationRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccommodationRepositoryImpl implements AccommodationRepositoryCustom {

	private MongoTemplate mongoTemplate;

	@Autowired
	public AccommodationRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public List<Accommodation> findByFilters(AccommodationFilterDTO filters) {
		List<Criteria> criteriaList = new ArrayList<>();

		if (filters.city() != null && !filters.city().isBlank()) {
			criteriaList.add(Criteria.where("city").is(filters.city()));
		}

		if (filters.state() != null && !filters.state().isBlank()) {
			criteriaList.add(Criteria.where("state").is(filters.state()));
		}

		if (filters.neighborhood() != null && !filters.neighborhood().isBlank()) {
			criteriaList.add(Criteria.where("neighborhood").is(filters.neighborhood()));
		}

		if (filters.accommodationType() != null) {
			criteriaList.add(Criteria.where("type").is(filters.accommodationType()));
		}

		if (filters.priceMin() != null) {
			criteriaList.add(Criteria.where("price").gte(filters.priceMin()));
		}

		if (filters.priceMax() != null) {
			criteriaList.add(Criteria.where("price").lte(filters.priceMax()));
		}

		if (filters.maxOccupancyMin() != null) {
			criteriaList.add(Criteria.where("maxOccupancy").gte(filters.maxOccupancyMin()));
		}

		if (filters.allowsPets() != null) {
			criteriaList.add(Criteria.where("allowsPets").is(filters.allowsPets()));
		}

		if (filters.allowsChildren() != null) {
			criteriaList.add(Criteria.where("allowsChildren").is(filters.allowsChildren()));
		}

		if (filters.isSharedHosting() != null) {
			criteriaList.add(Criteria.where("isSharedHosting").is(filters.isSharedHosting()));
		}

		Query query;
		if (criteriaList.isEmpty()) {
			query = new Query();
		} else {
			Criteria combinedCriteria = new Criteria().andOperator(
				criteriaList.toArray(new Criteria[0])
			);
			query = new Query(combinedCriteria);
		}

		return mongoTemplate.find(query, Accommodation.class);
	}

}

