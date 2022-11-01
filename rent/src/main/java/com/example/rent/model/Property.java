package com.example.rent.model;

import com.example.rent.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "TB_PROPERTIES")
//@Data @AllArgsConstructor @NoArgsConstructor
@Builder
public class Property  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "amount", length = 10)
	private Integer amount;

	@Column(name = "price")
	private Double price;
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private Status status;

	public Property(Long id, Integer amount, Double price, Status status) {
		this.id = id;
		this.amount = amount;
		this.price = price;
		this.status = status;
	}
	public Property() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
