package com.example.rent.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_rent")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Rent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rent")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_property")
    private Property property;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate startDateRent;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate endDateRent;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "date_Adjustment_IGPM")
    private LocalDate dateAdjustmentIGPM;
    private Double iptu;
    private Double water;
    private Double energy;
    private Double condominium;
    @Column(nullable = false)
    private Double value;
    @Column(columnDefinition = "TEXT")
    private String description;

}
