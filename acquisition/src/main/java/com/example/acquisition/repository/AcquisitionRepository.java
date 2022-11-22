package com.example.acquisition.repository;

import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.example.acquisition.model.Acquisition;
import com.example.acquisition.model.Property;
import com.example.acquisition.model.User;

@Repository
public interface AcquisitionRepository extends JpaRepository<Acquisition, Long> {

    List<Acquisition> findAllAcquisitionsByUser(User user);

    Acquisition findAcquisitionByProperty(Property property);

    Acquisition findAcquisitionById(Long id);

}