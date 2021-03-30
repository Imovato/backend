package com.example.acquisition.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.acquisition.model.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

}
