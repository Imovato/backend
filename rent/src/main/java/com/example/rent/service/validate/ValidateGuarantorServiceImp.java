package com.example.rent.service.validate;

import com.example.rent.exceptions.ValidationException;
import com.example.rent.entities.Customer;
import com.example.rent.entities.Property;
import com.example.rent.service.interfaces.IValidationService;

public class ValidateGuarantorServiceImp implements IValidationService {

//    public static boolean hasGuarantor(Customer customer) {
//        return customer.isGuarantor();
//    }

    @Override
    public void validate(Customer customer, Property property) throws ValidationException {
        //if (hasGuarantor(customer) != true) {
       //     throw new ValidationException("Necessário um fiador para o aluguel!");
       // }
    }
}
