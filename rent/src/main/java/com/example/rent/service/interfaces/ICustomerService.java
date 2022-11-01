package com.example.rent.service.interfaces;

import com.example.rent.model.Customer;

public interface ICustomerService {
    Customer findCustomerById(Long id);
}
