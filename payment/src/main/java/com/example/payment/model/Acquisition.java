package com.example.payment.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.example.payment.dto.AcquisitionDTO;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public static Acquisition createAcquisition(AcquisitionDTO acquisitionDTO) {
        return new ModelMapper().map(acquisitionDTO, Acquisition.class);
    }
}
