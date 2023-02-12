package com.example.rent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.rent.model.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerById(Long id);
}
