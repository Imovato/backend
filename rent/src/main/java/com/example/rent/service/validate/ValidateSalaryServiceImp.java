package com.example.rent.service.validate;

import com.example.rent.exceptions.ValidationException;
import com.example.rent.entities.Customer;
import com.example.rent.entities.Property;
import com.example.rent.service.interfaces.IValidationService;

public class ValidateSalaryServiceImp implements IValidationService {

    public static boolean isSalary(Customer customer) {
        return (customer.getSalary() > 1211D);
    }

    @Override
    public void validate(Customer customer, Property property) throws ValidationException{
        if (isSalary(customer) != true) {
            throw new ValidationException("Seu salário é insuficiente para o aluguel!");
        }
    }
}
