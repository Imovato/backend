package com.example.rent.entities;

import com.example.rent.enums.InviteStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingInvite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Booking booking;

    @ManyToOne
    private User guest;

    @Enumerated(EnumType.STRING)
    private InviteStatus status;

    @Column(precision = 12, scale = 2)
    private BigDecimal shareAmount;

    private LocalDateTime deadline;

    private LocalDateTime createdAt;
}

