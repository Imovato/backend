package com.example.payment.interfaces.service;

import com.example.payment.model.Property;
import com.example.payment.model.User;

public interface IValidarCPFService {
    void validar(User user, Property property);
}
