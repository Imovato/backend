package com.example.rent.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "tbl_rents")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@DateTimeFormat(pattern = "MM/dd/yyy")
    @Column(name = "data", nullable = false)
    private Date data;*/

    @Column(name = "value", nullable = false)
    private Double value;

    private Integer amount;

    @OneToOne(cascade = CascadeType.ALL)
    private Property property;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = true) // ser false, porque obrigatoriamente precisa ter um user para rent
    private User user;

}
