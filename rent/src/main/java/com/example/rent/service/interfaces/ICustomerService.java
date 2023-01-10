package com.example.rent.service.interfaces;

import com.example.rent.model.Customer;
import com.example.rent.model.Property;

public interface ICustomerService {
    Customer findCustomerById(Long id);
    void validateCustomer(Customer customer, Property property);
}
