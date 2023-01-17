package com.example.rent.model.composite;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tbl_personal_info")
public class PersonalInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_personal")
    private Long id;
    private String name;
    private String cpf;

}
