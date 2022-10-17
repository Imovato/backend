package com.example.rent.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_users")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Rent> rents;

}
