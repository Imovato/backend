package com.example.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.payment.model.PropertyRent;

@Repository
public interface PropertyRentRepository extends JpaRepository<PropertyRent, Long>{

}
