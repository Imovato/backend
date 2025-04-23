package com.example.rent.resources;

import com.example.rent.dto.BookingDto;
import com.example.rent.entities.Booking;
import com.example.rent.response.RentResponse;
import com.example.rent.service.BookingService;
import com.example.rent.service.RentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingResource {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private RentService rentService;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody BookingDto request) {
        Booking response = bookingService.createBooking(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/checkin/{idBooking}")
    @Operation(summary = "Faz checkin em uma reserva existente")
    public ResponseEntity<RentResponse> checkin(@PathVariable @Valid Long idBooking) {
        return new ResponseEntity<>(rentService.processCheckin(idBooking), HttpStatus.CREATED);
    }

    //consultar reserva

    //atualizar reserva

    //deletar reserva

}
