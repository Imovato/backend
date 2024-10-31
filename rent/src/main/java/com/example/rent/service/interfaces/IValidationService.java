package com.example.rent.service.interfaces;

import com.example.rent.entities.Accommodation;
import com.example.rent.entities.User;

public interface IValidationService {
    void validate(User user, Accommodation accommodation);
}
