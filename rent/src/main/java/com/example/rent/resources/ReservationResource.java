package com.example.rent.resources;

import com.example.rent.dto.ReservationDto;
import com.example.rent.entities.Reservation;
import com.example.rent.repository.AccommodationRepository;
import com.example.rent.repository.ReservationRepository;
import com.example.rent.repository.UserRepository;
import com.example.rent.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationResource {

    @Autowired
    private ReservationService reservationService;


    @PostMapping
    public ResponseEntity<Reservation> criarReserva(@RequestBody ReservationDto request) {
        Reservation response = reservationService.criarReserva(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
