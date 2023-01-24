package com.example.rent.service.impl;

import com.example.rent.model.Property;
import com.example.rent.repository.PropertyRepository;
import com.example.rent.service.interfaces.IPropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PropertyServiceImp implements IPropertyService {

    private final PropertyRepository propertyRepository;

    @Override
    public Property findPropertyById(Long id) {
        return propertyRepository.findPropertyById(id);
    }

    @Override
    public void updateProperty(Property property) {
         propertyRepository.save(property);
    }

    @Override
    public Property save(Property property){
        return propertyRepository.save(property);
    }

}
