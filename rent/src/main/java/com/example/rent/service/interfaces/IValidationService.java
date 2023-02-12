package com.example.rent.service.interfaces;

import com.example.rent.model.Customer;
import com.example.rent.model.Property;

public interface IValidationService {
    void validate(Customer customer, Property property);
}
