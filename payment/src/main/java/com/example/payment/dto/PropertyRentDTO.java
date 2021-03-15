package com.example.payment.dto;

public class PropertyRentDTO {

	private Long id;
	private Long idProperty;
	private Integer amount;

	public PropertyRentDTO() {
	}

	public PropertyRentDTO(Long id, Long idProperty, Integer amount) {
		this.id = id;
		this.idProperty = idProperty;
		this.amount = amount;
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

}
