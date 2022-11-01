package com.example.payment.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Setter
@Getter
@Entity
public class Acquisition {

    @Id
    private long id;

    @DateTimeFormat(pattern = "MM/dd/yyy")
    @Column(name = "data", nullable = false)
    private Date date;

    @OneToOne(cascade = CascadeType.ALL)
    private Property property;

    @Column(name = "value", nullable = false)
    private Double value;

    public Acquisition() {
    }

    public Acquisition(long id, Date date, Property property, Double value) {
        this.id = id;
        this.date = date;
        this.property = property;
        this.value = value;
    }

}
