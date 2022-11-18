package com.example.acquisition.interfaces.services;

import com.example.acquisition.model.Property;
import com.example.acquisition.model.User;

import java.util.Collection;

public interface IValidacaoService {

    void validate(User user, Property property);
}
