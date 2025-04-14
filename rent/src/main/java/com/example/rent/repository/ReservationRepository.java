package com.example.rent.repository;

import com.example.rent.entities.Reservation;
import com.example.rent.enums.StatusReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByStatusReservationAndExpiresDateBefore(StatusReservation statusReservation, LocalDateTime now);

}
