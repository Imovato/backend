package com.example.rent.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class ParticipanteReserva {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User guest;

    @ManyToOne
    private Reservation reservation;

    private boolean isPaid;
    private LocalDateTime paymentDate;

}
