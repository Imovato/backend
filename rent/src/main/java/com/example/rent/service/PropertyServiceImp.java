package com.example.rent.service;

import com.example.rent.interfaces.services.IPropertyService;
import com.example.rent.model.Property;
import com.example.rent.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyServiceImp implements IPropertyService {

    private PropertyRepository propertyRepository;

    @Autowired
    public PropertyServiceImp(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public Property findPropertyById(Long id) {
        return propertyRepository.findPropertyById(id);
    }
}
