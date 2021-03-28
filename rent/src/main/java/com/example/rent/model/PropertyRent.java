package com.example.rent.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "property_rent")
public class PropertyRent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "id_property")
	private Long idProperty;

	@Column(name = "amount")
	private Integer amount;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_rent")
	private Rent rent;

	public Long getId() {
		return id;
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

	public Rent getRent() {
		return rent;
	}

	public void setRent(Rent rent) {
		this.rent = rent;
	}

}
