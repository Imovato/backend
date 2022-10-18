package com.example.rent.interfaces.services;

import com.example.rent.model.Customer;

public interface ICustomerService {
    Customer findCustomerById(Long id);
}
