package com.example.payment.dto;

import java.util.Date;


import com.example.payment.model.Acquisition;
import com.example.payment.model.Property;
import org.modelmapper.ModelMapper;

public class AcquisitionDTO {
	
    private long id;
    private Date date;
    private Property property;
    private Double value;
    
    
    
	public AcquisitionDTO(long id, Date date, Property property, Double value) {
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

	public static AcquisitionDTO createAcquisition(Acquisition acquisition){
		return new ModelMapper().map(acquisition, AcquisitionDTO.class);
	}
}
