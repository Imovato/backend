package com.example.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.payment.model.Acquisition;

@Repository
public interface AcquisitionRepository extends JpaRepository<Acquisition, Long> {

	Acquisition findAcquisitionById(Long id);

}
