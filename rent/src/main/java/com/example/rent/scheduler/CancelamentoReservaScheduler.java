package com.example.rent.scheduler;

import com.example.rent.entities.Reservation;
import com.example.rent.enums.StatusReservation;
import com.example.rent.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class CancelamentoReservaScheduler {

    @Autowired
    private ReservationRepository repository;

    @Scheduled
    public void cancelarReservasExpiradasPorFaltaDePagamentoNoPeriodo() {

        LocalDateTime now = LocalDateTime.now();
        List<Reservation> expires = repository.findByStatusReservationAndExpiresDateBefore(StatusReservation.WAITING, now);

        for (Reservation reservation : expires) {
            reservation.setStatusReservation(StatusReservation.CANCELED);
            repository.save(reservation);

            System.out.println("Reserva " + reservation.getId() + " foi cancelada por expiração.");
        }
    }
}
