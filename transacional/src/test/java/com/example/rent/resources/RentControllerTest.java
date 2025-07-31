package com.example.rent.resources;

import com.example.rent.entities.Accommodation;
import com.example.rent.entities.User;
import com.example.rent.response.RentResponse;
import com.example.rent.service.RentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class RentControllerTest {


    @Mock
    private RentService rentService;



    private RentResponse rentResponse;

    @BeforeEach
    void setUp() {

        Accommodation accommodation = new Accommodation();
        accommodation.setId(6L);
        accommodation.setPrice(100.0);

        User user = new User();
        user.setId(2L);
        user.setName("Cooper");

        rentResponse = new RentResponse(
                accommodation,
                LocalDate.of(2023, 1, 17),
                LocalDate.of(2023, 1, 17)
        );
    }

}