package com.example.payment.dto;

import com.example.payment.model.Acquisition;

public class PropertyAcquisitionDTO {

    private Long id;
    private Long idProperty;
    private Integer amount;
    private Acquisition acquisition;
    
	public PropertyAcquisitionDTO(Long id, Long idProperty, Integer amount, Acquisition acquisition) {
		this.id = id;
		this.idProperty = idProperty;
		this.amount = amount;
		this.acquisition = acquisition;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdProperty() {
		return idProperty;
	}

	public void setIdProperty(Long idProperty) {
		this.idProperty = idProperty;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Acquisition getAcquisition() {
		return acquisition;
	}

	public void setAcquisition(Acquisition acquisition) {
		this.acquisition = acquisition;
	}
    
    
	
}
