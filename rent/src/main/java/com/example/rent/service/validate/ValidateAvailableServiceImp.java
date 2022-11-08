package com.example.rent.service.validate;

import com.example.rent.exceptions.ValidationException;
import com.example.rent.model.Customer;
import com.example.rent.model.Property;
import com.example.rent.service.interfaces.IPropertyService;
import com.example.rent.service.interfaces.IValidationService;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidateAvailableServiceImp implements IValidationService {

    @Autowired
    private IPropertyService propertyService;

    @Override
    public void validate(Customer customer, Property property) {
        if (propertyService.isAvailable(property) != true) {
            throw new ValidationException("Esta propriedade está indisponível!");
        }
    }
}
