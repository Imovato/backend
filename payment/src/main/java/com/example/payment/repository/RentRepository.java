package com.example.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.payment.model.Acquisition;
import com.example.payment.model.Rent;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {
	Rent findRentById(Long id);
}
