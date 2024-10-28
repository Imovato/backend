package com.example.rent.service.impl;

import com.example.rent.entities.Customer;
import com.example.rent.entities.Property;
import com.example.rent.repository.CustomerRepository;
import com.example.rent.service.interfaces.ICustomerService;
import com.example.rent.service.interfaces.IValidationService;
import com.example.rent.service.validate.ValidateCpfServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomerServiceImp implements ICustomerService {

    private final CustomerRepository customerRepository;

    private List<IValidationService> validations = Arrays.asList(new ValidateCpfServiceImp());

    @Override
    public Customer findCustomerById(Long id) {
        return customerRepository.findCustomerById(id);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void validateCustomer(Customer customer, Property property) {
        this.validations.forEach(element->element.validate(customer, property));
    }
}
