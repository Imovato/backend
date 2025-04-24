package com.example.rent.entities;

import com.example.rent.enums.StatusReservation;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Accommodation accommodation;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<GuestBooking> guests;

    @Enumerated(EnumType.STRING)
    private StatusReservation statusReservation;

    private LocalDate initialDate;
    private LocalDate endDate;
    private LocalDateTime creationDate;
    private LocalDateTime expiresDate;

}
