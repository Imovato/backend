package com.example.rent.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_guarantor")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Guarantor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_guarantor")
    private Long id;
    private String name;
    private String cpf;
    private Long rg;
    private String nationality;
    private String profession;
    private String phone;
    private String cep;
    private String district;
    private String address;
    private String city;
    private String uf;
}
