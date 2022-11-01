package com.example.rent.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "TB_RENTS")
//@Data @AllArgsConstructor @NoArgsConstructor
@Builder @ToString
public class Rent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = true)
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    private Property property;

    /*@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy/MM/dd")
    @DateTimeFormat(pattern="yyyy/MM/dd")
    @Column(columnDefinition = "DATE", name = "startDateRent", nullable = false)
    private LocalDate startDateRent;

    /*@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy/MM/dd")
    @DateTimeFormat(pattern="yyyy/MM/dd")
    @Column(columnDefinition = "DATE", name = "endDateRent", nullable = false)
    private LocalDate endDateRent;*/

    @Column(name = "value", nullable = false)
    private Double value;
    private Integer amount;
    private Integer expirationDay;
    private Double iptu;
    @Column(columnDefinition = "TEXT")
    private String description;

    public Rent(Long id, Customer customer, Property property, Double value, Integer amount, Integer expirationDay, Double iptu, String description) {
        this.id = id;
        this.customer = customer;
        this.property = property;
        this.value = value;
        this.amount = amount;
        this.expirationDay = expirationDay;
        this.iptu = iptu;
        this.description = description;
    }
    public Rent( ) {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getExpirationDay() {
        return expirationDay;
    }

    public void setExpirationDay(Integer expirationDay) {
        this.expirationDay = expirationDay;
    }

    public Double getIptu() {
        return iptu;
    }

    public void setIptu(Double iptu) {
        this.iptu = iptu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
