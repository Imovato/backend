package com.example.rent.repository;

import com.example.rent.entities.BookingInvite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingInviteRepository extends JpaRepository<BookingInvite, Long> {
}

