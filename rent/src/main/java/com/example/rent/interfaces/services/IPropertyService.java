package com.example.rent.interfaces.services;

import com.example.rent.model.Property;
import java.util.UUID;

public interface IPropertyService {
    Property findPropertyById(UUID id);
    void updateProperty(Property property);
}