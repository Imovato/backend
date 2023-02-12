package com.example.rent.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.rent.model.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    Property findPropertyById(Long id);
}
