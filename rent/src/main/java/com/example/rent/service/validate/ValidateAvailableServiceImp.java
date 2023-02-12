package com.example.rent.service.validate;

import com.example.rent.enums.Status;
import com.example.rent.exceptions.ValidationException;
import com.example.rent.model.Customer;
import com.example.rent.model.Property;
import com.example.rent.service.interfaces.IValidationService;

public class ValidateAvailableServiceImp implements IValidationService {

    private boolean isAvailable(Property property) {
        if (property.getStatus().equals(Status.AVAILABLE)) return true;
        return false;
    }

    @Override
    public void validate(Customer customer, Property property) throws ValidationException{
        if (isAvailable(property) != true) {
            throw new ValidationException("Esta propriedade está indisponível!");
        }
    }
}
