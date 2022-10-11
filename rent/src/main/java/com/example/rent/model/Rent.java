package com.example.rent.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tbl_rents")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    /*@DateTimeFormat(pattern = "MM/dd/yyy")
    @Column(name = "data", nullable = false)
    private Date data;*/

    @Column(name = "value", nullable = false)
    private Double value;

    private Integer amount;

    @OneToOne(cascade = CascadeType.ALL)
    private Property property;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

}
