package com.example.rent.service.impl;

import com.example.rent.model.Property;
import com.example.rent.service.interfaces.ICustomerService;
import com.example.rent.model.Customer;
import com.example.rent.repository.CustomerRepository;
import com.example.rent.service.interfaces.IValidationService;
import com.example.rent.service.validate.ValidateCpfServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void validateCustomer(Customer customer, Property property) {
        this.validations.forEach(element->element.validate(customer, property));
    }
}
