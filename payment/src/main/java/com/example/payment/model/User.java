package com.example.payment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    private Long idUser;

    @Column(name = "name")
    private String name;

    @Column(name = "Cpf")
    private String Cpf;

    @OneToMany
    private List<Rent> rents;

}
