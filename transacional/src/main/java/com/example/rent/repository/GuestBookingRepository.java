package com.example.rent.repository;

import com.example.rent.entities.GuestBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestBookingRepository extends JpaRepository<GuestBooking, Long> {
    long countByReservation_Id(Long reservationId);
}

