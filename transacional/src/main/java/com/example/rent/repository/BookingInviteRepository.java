package com.example.rent.repository;

import com.example.rent.entities.BookingInvite;
import com.example.rent.enums.InviteStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingInviteRepository extends JpaRepository<BookingInvite, Long> {
    List<BookingInvite> findByGuest_IdAndStatus(String guestId, InviteStatus status);
}
