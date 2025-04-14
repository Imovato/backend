package com.example.rent.service;

import com.example.rent.dto.ReservationDto;
import com.example.rent.entities.Reservation;
import org.springframework.stereotype.Service;

public interface ReservationService {
    Reservation criarReserva(ReservationDto request);
}
