package com.example.rent.service.interfaces;

import com.example.rent.entities.Customer;
import com.example.rent.entities.Property;

public interface IValidationService {
    void validate(Customer customer, Property property);
}
