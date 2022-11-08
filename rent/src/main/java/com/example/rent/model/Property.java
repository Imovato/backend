package com.example.rent.model;

import com.example.rent.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TB_PROPERTY")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Property  implements Serializable {

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

}
