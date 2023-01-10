package com.example.rent.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_customer")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer")
    private Long id;
    private String name;
    private String cpe;
    private String address;
    private String city;
    private String district;
    private String uf;
    private String cpf;
    private Double salary;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_guarantor")
    private Guarantor guarantor;

//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "customer_id")
//    private List<Rent> rents = new ArrayList<>();

}
