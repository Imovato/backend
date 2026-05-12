package com.unipampa.crud.repository.impl;

import com.unipampa.crud.dto.AccommodationFilterDTO;
import com.unipampa.crud.entities.Accommodation;
import com.unipampa.crud.enums.AccommodationStats;
import com.unipampa.crud.repository.AccommodationRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository

public class AccommodationRepositoryImpl implements AccommodationRepositoryCustom {

	private static final Logger logger = LoggerFactory.getLogger(AccommodationRepositoryImpl.class);

	private MongoTemplate mongoTemplate;

	@Autowired
	public AccommodationRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public List<Accommodation> findByFilters(AccommodationFilterDTO filters) {
		List<Criteria> criteriaList = new ArrayList<>();

		// store enum as string to ensure the generated query uses BSON string values
		criteriaList.add(Criteria.where("stats").is(AccommodationStats.AVAILABLE.name()));

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
			// compare with the enum name (stored as String in the DB)
			criteriaList.add(Criteria.where("type").is(filters.accommodationType().name()));
		}

		if (filters.priceMin() != null || filters.priceMax() != null) {
			Criteria priceCriteria = Criteria.where("price");
			if (filters.priceMin() != null) {
				priceCriteria = priceCriteria.gte(filters.priceMin());
			}
			if (filters.priceMax() != null) {
				priceCriteria = priceCriteria.lte(filters.priceMax());
			}
			criteriaList.add(priceCriteria);
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

		if (logger.isDebugEnabled()) {
			try {
				logger.debug("Mongo Query: {}", query == null ? "{}" : query.getQueryObject().toJson());
			} catch (Exception e) {
				logger.debug("Mongo Query (toJson) failed, Query: {}", query);
			}
		}

		return mongoTemplate.find(query, Accommodation.class);
	}

}
