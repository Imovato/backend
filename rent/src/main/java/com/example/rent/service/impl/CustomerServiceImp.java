package com.example.rent.service.impl;

import com.example.rent.model.Property;
import com.example.rent.service.interfaces.ICustomerService;
import com.example.rent.model.Customer;
import com.example.rent.repository.CustomerRepository;
import com.example.rent.service.interfaces.IValidationService;
import com.example.rent.service.validate.ValidateAvailableServiceImp;
import com.example.rent.service.validate.ValidateCpfServiceImp;
import com.example.rent.service.validate.ValidateGuarantorServiceImp;
import com.example.rent.service.validate.ValidateSalaryServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
@AllArgsConstructor
public class CustomerServiceImp implements ICustomerService {

    private CustomerRepository customerRepository;

    List<IValidationService> validation = new ArrayList<>();
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


    @Override
    public void validateCustomer(Customer customer, Property property) {
        validation.forEach(element->element.validate(customer,property));
    }


}
