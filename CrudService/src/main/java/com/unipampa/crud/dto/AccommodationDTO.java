package com.unipampa.crud.dto;

import com.unipampa.crud.enums.AccommodationType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AccommodationDTO {

	@NotBlank
	private String title;

	private String neighborhood;

	private String codAddress;

	@NotNull
	private String city;

	@NotBlank
	private String description;

	private String adress;

	@NotBlank
	private String state;

	@NotBlank
	private double price;

	private Long number;

	@NotNull
	private int imageQuantity;

	@NotNull
	private AccommodationType accommodationType;

}
