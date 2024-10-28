package com.example.rent.repository;

import com.example.rent.entities.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {

	List<Rent> findByCustomerId(Long customerId);
}
