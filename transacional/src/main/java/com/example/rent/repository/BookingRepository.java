package com.example.rent.repository;

import com.example.rent.entities.Booking;
import com.example.rent.enums.StatusReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByStatusReservationAndExpiresDateBefore(StatusReservation statusReservation, LocalDateTime now);

}
