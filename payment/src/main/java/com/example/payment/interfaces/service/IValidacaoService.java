package com.example.payment.interfaces.service;

import com.example.payment.model.Property;
import com.example.payment.model.User;

public interface IValidacaoService {

    void validar(User user, Property property);
}
