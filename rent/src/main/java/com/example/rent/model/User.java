package com.example.rent.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class User {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany
    private List<Rent> rents;

    public User() {
    }

    public User(Long id, String name, List<Rent> rents) {
        this.id = id;
        this.name = name;
        this.rents = rents;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
