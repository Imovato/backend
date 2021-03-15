package com.example.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.payment.model.PropertyAcquisition;

@Repository
public interface PropertyAcquisitionRepository extends JpaRepository<PropertyAcquisition, Long>{

}
