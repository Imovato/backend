package com.example.acquisition.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.acquisition.model.Acquisition;

@Repository
public interface AcquisitionRepository extends JpaRepository<Acquisition, Long> {

}