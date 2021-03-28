package com.example.rent.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rent.model.Rent;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {
	Rent findRentById(Long id);
}
