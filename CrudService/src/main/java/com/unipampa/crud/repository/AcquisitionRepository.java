package com.unipampa.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unipampa.crud.model.Acquisition;

public interface AcquisitionRepository extends JpaRepository<Acquisition, Long>{

	Acquisition findAcquisitionById(Long id);


}
