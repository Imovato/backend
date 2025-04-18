package com.example.rent.resources;

import com.example.rent.dto.ReservationDto;
import com.example.rent.entities.Reservation;
import com.example.rent.response.RentResponse;
import com.example.rent.service.RentService;
import com.example.rent.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationResource {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private RentService rentService;

    @PostMapping
    public ResponseEntity<Reservation> criarReserva(@RequestBody ReservationDto request) {
        Reservation response = reservationService.criarReserva(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/checkin/{idReservation}")
    @Operation(summary = "Faz checkin em uma reserva existente")
    public ResponseEntity<RentResponse> checkin(@PathVariable @Valid Long idReservation) {
        return new ResponseEntity<>(rentService.processCheckin(idReservation), HttpStatus.CREATED);
    }

    //consultar reserva

    //atualizar reserva

    //deletar reserva

}
