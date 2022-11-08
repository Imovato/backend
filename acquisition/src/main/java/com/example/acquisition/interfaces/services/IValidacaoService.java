package com.example.acquisition.interfaces.services;

import com.example.acquisition.model.Property;
import com.example.acquisition.model.User;

public interface IValidacaoService {

    void validar(User user, Property property);
}
