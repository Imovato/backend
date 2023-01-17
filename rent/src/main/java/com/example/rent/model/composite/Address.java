package com.example.rent.model.composite;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tbl_address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address")
    private Long id;
    private String cpe;
    private String district;
    private String address;
    private String city;
    private String uf;
}
