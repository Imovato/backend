package com.example.rent.service.validate;

import com.example.rent.entities.User;
import com.example.rent.exceptions.ValidationException;
import com.example.rent.entities.Accommodation;
import com.example.rent.service.interfaces.IValidationService;

public class ValidateGuarantorServiceImp implements IValidationService {

//    public static boolean hasGuarantor(Customer customer) {
//        return customer.isGuarantor();
//    }

    @Override
    public void validate(User user, Accommodation property) throws ValidationException {
        //if (hasGuarantor(customer) != true) {
       //     throw new ValidationException("Necessário um fiador para o aluguel!");
       // }
    }
}
