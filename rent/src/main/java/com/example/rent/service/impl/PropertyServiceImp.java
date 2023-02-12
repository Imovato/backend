package com.example.rent.service.impl;

import com.example.rent.service.interfaces.IPropertyService;
import com.example.rent.model.Property;
import com.example.rent.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyServiceImp implements IPropertyService {
    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public Property findPropertyById(Long id) {
        return propertyRepository.findPropertyById(id);
    }

    @Override
    public void updateProperty(Property property) {
         propertyRepository.save(property);
    }

}
