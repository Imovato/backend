package com.example.acquisition.service;

import com.example.acquisition.exceptions.ValidacaoException;
import com.example.acquisition.interfaces.services.IPropertyService;
import com.example.acquisition.interfaces.services.IValidacaoService;
import com.example.acquisition.model.Property;
import com.example.acquisition.model.User;

public class ValidaRendaServiceImp implements IValidacaoService {

    private IPropertyService propertyService;

    @Override
    public void validate(User user, Property property) throws ValidacaoException { //tem mais coisas pra levar em conta, mais pra frente REVER!!!
        if(user.getRenda() * 0.3 < propertyService.calculaParcela(property)){
            throw new ValidacaoException("Sua renda mensal é insuficiente para a compra!");
        }
    }    
}
