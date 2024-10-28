package com.example.rent.service.interfaces;

import com.example.rent.entities.Customer;
import com.example.rent.entities.Property;

public interface ICustomerService {
    Customer save(Customer customer);
    Customer findCustomerById(Long id);
    void validateCustomer(Customer customer, Property property);
}
