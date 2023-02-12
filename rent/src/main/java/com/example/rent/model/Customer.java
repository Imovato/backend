package com.example.rent.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_CUSTOMER")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "customer_id")
    private Long id;

    @Column(name = "name")
    private String name;
    private String cpf;
    private Double salary;
    private boolean guarantor;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "customer_id")
    private List<Rent> rents = new ArrayList<>();

}
