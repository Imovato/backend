package com.example.rent.service.interfaces;

import com.example.rent.model.Property;

public interface IPropertyService {
    Property findPropertyById(Long id);
    void updateProperty(Property property);

    boolean isAvailable(Property property);
}