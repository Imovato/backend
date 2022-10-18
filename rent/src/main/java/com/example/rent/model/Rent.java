package com.example.rent.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Date;

@Entity
@Table(name = "TB_RENTS")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Rent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = true) // ser false, porque obrigatoriamente precisa ter um user para rent
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    private Property property;

    @DateTimeFormat(pattern="dd/MM/yyyy")
    @Column(name = "startDateRent", nullable = false)
    private LocalDate startDateRent;

    @DateTimeFormat(pattern="dd/MM/yyyy")
    @Column(name = "endDateRent", nullable = false)
    private LocalDate endDateRent;

    @Column(name = "value", nullable = false)
    private Double value;
    private Integer expirationDay;
    private Double finePercentage;
    @Column(columnDefinition = "TEXT")
    private String note;
    private Integer amount;

    public StringBuilder contractTime(Date startDate, Date endDate) {
        long time = ChronoUnit.MONTHS.between((Temporal) startDate, (Temporal) endDate);
        return new StringBuilder().append(time).append(" MESES");
    }






}
