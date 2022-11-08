package com.example.rent.service.validate;

import com.example.rent.exceptions.ValidationException;
import com.example.rent.model.Customer;
import com.example.rent.model.Property;
import com.example.rent.service.interfaces.IValidationService;

public class ValidateSalaryServiceImp implements IValidationService {

    public static boolean isSalary(Customer customer) {
        return (customer.getSalary() > 1211D);
    }

    @Override
    public void validate(Customer customer, Property property) {
        if (isSalary(customer) != true) {
            throw new ValidationException("Seu salário é insuficiente para o aluguel!");
        }
    }
}
