package com.example.rent.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TB_USERS")
//@Data @AllArgsConstructor @NoArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_id")
    private Long id;

    @Column(name = "name")
    private String name;
    private String cpf;
    private Double salary;
    private boolean guarantor;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Rent> rents;

    public Customer(Long id, String name, String cpf, Double salary, boolean guarantor, List<Rent> rents) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.salary = salary;
        this.guarantor = guarantor;
        this.rents = rents;
    }
    public Customer() {

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public boolean isGuarantor() {
        return guarantor;
    }

    public void setGuarantor(boolean guarantor) {
        this.guarantor = guarantor;
    }

    public List<Rent> getRents() {
        return rents;
    }

    public void setRents(List<Rent> rents) {
        this.rents = rents;
    }
}
