package com.example.payment.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
}