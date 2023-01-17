package com.example.rent.model.composite;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tbl_contact_info")
public class ContactInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contact")
    private Long id;
    private String phone;
    private String email;
}
