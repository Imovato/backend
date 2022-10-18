package com.example.rent.service;

import com.example.rent.interfaces.services.ICustomerService;
import com.example.rent.model.Customer;
import com.example.rent.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerServiceImp implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImp(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer findCustomerById(Long id) {
        return customerRepository.findCustomerById(id);
    }
}
