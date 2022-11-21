package com.example.acquisition.service;

import com.example.acquisition.enums.Status;
import com.example.acquisition.exceptions.ValidacaoException;
import com.example.acquisition.interfaces.services.IPropertyService;
import com.example.acquisition.interfaces.services.IValidacaoService;
import com.example.acquisition.model.Property;
import com.example.acquisition.model.User;

public class ValidaStatusServiceImp implements IValidacaoService {

    private IPropertyService propertyService;

    @Override
    public void validate(User user, Property property) throws ValidacaoException {
        if(!property.getStatus().equals(Status.AVAILABLE)) {
            throw new ValidacaoException("Essa casa não esta disponível, ja foi vendida!");
        }
    }
}
