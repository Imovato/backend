package com.example.rent.interfaces.services;

import com.example.rent.model.Property;

public interface IPropertyService {
    Property findPropertyById(Long id);
    void updateProperty(Property property);
}