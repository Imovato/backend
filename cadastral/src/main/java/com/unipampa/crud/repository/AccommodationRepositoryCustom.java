package com.unipampa.crud.repository;

import com.unipampa.crud.dto.AccommodationFilterDTO;
import com.unipampa.crud.entities.Accommodation;

import java.util.List;


public interface AccommodationRepositoryCustom {

    /**
     * Busca acomodações com base em filtros opcionais usando Criteria do MongoDB
     * Todos os filtros são opcionais - apenas campos não nulos serão utilizados
     *
     * @param filters DTO contendo os filtros opcionais
     * @return Lista de acomodações que correspondem aos filtros
     */
    List<Accommodation> findByFilters(AccommodationFilterDTO filters);

}

