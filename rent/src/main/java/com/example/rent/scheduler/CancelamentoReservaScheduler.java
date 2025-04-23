package com.example.rent.scheduler;

import com.example.rent.entities.Booking;
import com.example.rent.enums.StatusReservation;
import com.example.rent.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class CancelamentoReservaScheduler {

    @Autowired
    private BookingRepository repository;

    @Scheduled
    public void cancelarReservasExpiradasPorFaltaDePagamentoNoPeriodo() {

        LocalDateTime now = LocalDateTime.now();
        List<Booking> expires = repository.findByStatusReservationAndExpiresDateBefore(StatusReservation.WAITING_PAYMENT, now);

        for (Booking reservation : expires) {
            reservation.setStatusReservation(StatusReservation.CANCELED);
            repository.save(reservation);

            System.out.println("Reserva " + reservation.getId() + " foi cancelada por expiração.");
        }
    }
}
