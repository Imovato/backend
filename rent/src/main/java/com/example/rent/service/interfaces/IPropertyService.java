package com.example.rent.service.interfaces;

import com.example.rent.model.Property;

public interface IPropertyService {
    Property save(Property property);
    Property findPropertyById(Long id);
    void updateProperty(Property property);
}
