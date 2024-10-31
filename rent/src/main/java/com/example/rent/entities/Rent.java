package com.example.rent.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_rent")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rent")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private Accommodation accommodation;

    private User user;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateRent;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDateRent;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDateRent;

    @NotNull(message = "The value of rent cannot be empty")
    private Double price;

    private int numberAdults;

    private int numberChild;

    private int numberBaby;

    private int numberPets;

    private boolean userCancel;

    private LocalDate dateCancel;

    private String reasonCancellation;

    private Double cancellationFee;

    private boolean refund;

    private Double refundValue;

}
