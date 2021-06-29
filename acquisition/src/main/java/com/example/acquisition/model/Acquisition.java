package com.example.acquisition.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;

import com.example.acquisition.dto.AcquisitionDTO;
import org.modelmapper.ModelMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Acquisition implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@DateTimeFormat(pattern = "MM/dd/yyy")
	@Column(name = "data", nullable = false)
	private Date data;
	@Column(name = "value", nullable = false)
	private Double value;
	@Column(name = "amount", length = 10)
	private Integer amount;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_property")
	private Property property;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_user")
	private User user;
}