package com.example.rent.resources;

import com.example.rent.dto.BookingDto;
import com.example.rent.entities.Booking;
import com.example.rent.enums.StatusReservation;
import com.example.rent.mapper.BookingMapper;
import com.example.rent.repository.BookingRepository;
import com.example.rent.response.RentResponse;
import com.example.rent.service.BookingService;
import com.example.rent.service.RentService;
import com.example.rent.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.Optional;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingResource {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private RentService rentService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookingRepository bookingRepository;



    @PostMapping
    @Operation(summary = "Cria uma reserva para uma propriedade existente")
    public ResponseEntity<Booking> createBooking(@RequestBody BookingDto request) {
        Booking response = bookingService.createBooking(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/checkin/{idBooking}")
    @Operation(summary = "Faz checkin em uma reserva existente")
    public ResponseEntity<RentResponse> checkin(@PathVariable @Valid Long idBooking) {
        return new ResponseEntity<>(rentService.processCheckin(idBooking), HttpStatus.CREATED);
    }

    @Operation(summary = "Consulta uma reserva existente")
    @GetMapping("/{id}")
    public ResponseEntity<BookingDto> getBookingById(@PathVariable Long id) throws Exception {
        Booking booking = bookingService.getBookingById(id);

        return ResponseEntity.ok(BookingMapper.toDto(booking));
    }

    @Operation(summary = "Cancela uma reserva existente")
    @PatchMapping("/{id}/cancel")
    public ResponseEntity<BookingDto> cancelBookingById(@PathVariable Long id) throws Exception {
        Booking booking = bookingService.cancelBooking(id);
        return ResponseEntity.ok(BookingMapper.toDto(booking));
    }

    @PatchMapping("/{id}/pay/{userId}")
    @Operation(summary = "Realiza o pagamento de uma reserva")
    public ResponseEntity<BookingDto> payBooking(@PathVariable Long id, @PathVariable Long userId) throws Exception {
        BookingDto bookingDto = bookingService.payBooking(id, userId);
        return ResponseEntity.ok(bookingDto);
    }

    @PostMapping("/forcar/cancelamento/{idReserva}")
    public ResponseEntity<String> forcarCancelamento(@PathVariable Long idReserva) {
        Optional<Booking> reservaOptional = bookingRepository.findById(idReserva);

        if (reservaOptional.isPresent()) {
            Booking reserva = reservaOptional.get();
            reserva.setStatusReservation(StatusReservation.CANCELED);
            bookingRepository.save(reserva);
            return ResponseEntity.ok("Reserva " + idReserva + " foi cancelada com sucesso.");
        } else {
            return ResponseEntity.status(404).body("Reserva com ID " + idReserva + " não encontrada.");
        }
    }



}
