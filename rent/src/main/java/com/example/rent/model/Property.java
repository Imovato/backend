package com.example.rent.model;

import com.example.rent.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tbl_properties")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Property {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private UUID id;

	@Column(name = "amount", length = 10)
	private Integer amount;

	@Column(name = "price")
	private double price;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private Status status;

}
