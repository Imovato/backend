package com.unipampa.scheduling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unipampa.scheduling.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
