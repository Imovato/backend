package com.example.rent.entities;

import com.example.rent.enums.StatusAccommodation;
import com.example.rent.enums.StatusReservation;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
@Entity
public class Reservation {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Accommodation accommodation;

    @OneToMany
    private List<ParticipanteReserva> guests;

    @Enumerated(EnumType.STRING)
    private StatusReservation statusReservation;

    private LocalDateTime creationDate;
    private LocalDateTime expiresDate;

}
