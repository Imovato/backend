package com.example.rent.service.impl;

import com.example.rent.entities.Accommodation;
import com.example.rent.entities.User;
import com.example.rent.enums.StatusAccommodation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RentServiceImplTest {

    private User user;

    private Accommodation accommodation;

    @BeforeEach
    void setUp() {
        accommodation = new Accommodation();
        accommodation.setId("10");
        accommodation.setPrice(100.0);
        accommodation.setStatus(StatusAccommodation.AVAILABLE);
        user = new User();
        user.setId("1");

    }

}