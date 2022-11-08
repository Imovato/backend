package com.example.rent.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "TB_RENT")
@Data @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class Rent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Customer customer;

    /*@OneToOne()
    private Property property;*/
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_property")
    private Property property;

   /* @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private Customer customer;*/

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    @DateTimeFormat(pattern="dd/MM/yyyy")
    @Column(columnDefinition = "DATE", name = "startDateRent", nullable = false)
    private LocalDate startDateRent;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    @DateTimeFormat(pattern="dd/MM/yyyy")
    @Column(columnDefinition = "DATE", name = "endDateRent", nullable = false)
    private LocalDate endDateRent;

    @Column(name = "value", nullable = false)
    private Double value;
    private Integer amount;
    private Integer expirationDay;
    private Double iptu;
    @Column(columnDefinition = "TEXT")
    private String description;

}
