package com.example.rent.resources;

import com.example.rent.dto.BookingDto;
import com.example.rent.entities.Booking;
import com.example.rent.entities.GuestBooking;
import com.example.rent.entities.User;
import com.example.rent.enums.StatusReservation;
import com.example.rent.mapper.BookingMapper;
import com.example.rent.response.RentResponse;
import com.example.rent.service.BookingService;
import com.example.rent.service.RentService;
import com.example.rent.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.List;
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

    @PostMapping
    @Operation(summary = "Cria uma reserva existente")
    public ResponseEntity<Booking> createBooking(@RequestBody BookingDto request) {
        Booking response = bookingService.createBooking(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/checkin/{idBooking}")
    @Operation(summary = "Faz checkin em uma reserva existente")
    public ResponseEntity<RentResponse> checkin(@PathVariable @Valid Long idBooking) {
        return new ResponseEntity<>(rentService.processCheckin(idBooking), HttpStatus.CREATED);
    }

    /* CONSULTAR RESERVA
     * Retornar detalhes da reserva.
     */
    @Operation(summary = "Consulta uma reserva existente")
    @GetMapping("/{id}")
    public ResponseEntity<BookingDto> getBookingById(@PathVariable Long id) throws Exception {
        Booking booking = bookingService.getBookingById(id);

        return ResponseEntity.ok(BookingMapper.toDto(booking));

    }

    /* CANCELAR RESERVA
     * cancelar os dados de uma reserva,
     * trocar a coluna status_reservation da tabela boooking para CANCELED
     */
    @Operation(summary = "Cancela uma reserva existente")
    @PatchMapping("/{id}/cancel")
    public ResponseEntity<BookingDto> cancelBookingById(@PathVariable Long id) throws Exception {
        Booking booking = bookingService.cancelBooking(id);
        return ResponseEntity.ok(BookingMapper.toDto(booking));
    }


    /* CRIAR O ENDPOINT DE PAGAMENTO DA RESERVA
     * - Validar o status_reservation da tabela boooking (se CANCELED ou CONFIRMED lançar exception)
     * - verificar o usuario que está pagando para alterar essas informações.
     * - trocar a coluna is_paid para true e popular a coluna payment_date
        com a data do pagamento.
     */

    @PatchMapping("/{id}/pay/{userId}")
    public ResponseEntity<BookingDto> payBooking(@PathVariable Long id, @PathVariable Long userId) throws Exception {
        Booking booking = bookingService.getBookingById(id);
        if (booking.getStatusReservation() == StatusReservation.CANCELED
                || booking.getStatusReservation() == StatusReservation.CONFIRMED ) throw new Exception("Erro ao tentar efetuar uma reserva");

        Optional<User> user = userService.findById(userId);

        if (user.isEmpty()) {
            throw new Exception("Usuário não encontrado");
        }

        List<GuestBooking> guestBookings = booking.getGuests();

        Optional<GuestBooking> matchingGuestBooking = guestBookings.stream()
                .filter(guestBooking -> guestBooking.getGuest().getId().equals(userId))
                .findFirst();

        if (matchingGuestBooking.isEmpty()) {
            throw new Exception("Usuário não está entre os convidados da reserva");
        }

        GuestBooking guestBooking = matchingGuestBooking.get();

        if(guestBooking.isPaid()) {
            throw new Exception("Usuário já efetuou o pagamento");
        }

        guestBooking.setPaid(true);
        guestBooking.setPaymentDate(LocalDateTime.now());

        Booking updatedBooking = bookingService.updateBooking(booking);

        BookingDto bookingDto = BookingMapper.toDto(updatedBooking);
        return ResponseEntity.ok(bookingDto);
    }

}
