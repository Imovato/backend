package com.example.acquisition.service;

import com.example.acquisition.exceptions.ValidacaoException;
import com.example.acquisition.interfaces.services.IPropertyService;
import com.example.acquisition.model.Property;
import com.example.acquisition.model.User;

public class ValidaRendaService implements ValidacaoService {

    private IPropertyService propertyService;

    @Override
    public void validar(User user, Property property) { //tem mais coisas pra levar em conta, mais pra frente REVER!!!
        if(user.getRenda() * 0.3 < propertyService.calculaParcela(property)){
            throw new ValidacaoException("Sua renda é insuficiente para a compra!");
        }
    }    
}
