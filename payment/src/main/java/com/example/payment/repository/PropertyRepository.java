package com.example.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.payment.model.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

}
