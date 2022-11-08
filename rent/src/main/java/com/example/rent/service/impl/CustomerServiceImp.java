package com.example.rent.service.impl;

import com.example.rent.service.interfaces.ICustomerService;
import com.example.rent.model.Customer;
import com.example.rent.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerServiceImp implements ICustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImp(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer findCustomerById(Long id) {
        return customerRepository.findCustomerById(id);
    }

    @Override
    public Customer findUserById(Long id) {
        return customerRepository.findUserById(id);
    }
}
