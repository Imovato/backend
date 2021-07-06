package com.unipampa.crud.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.unipampa.crud.model.Apartment;
import com.unipampa.crud.model.Ground;
import com.unipampa.crud.model.House;
import com.unipampa.crud.model.Property;
import lombok.*;
import lombok.experimental.Accessors;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PropertyDTO {

	private Long id;
	private @NonNull String name;
	private @NonNull float area;
	private @NonNull String neighborhood;
	private @NonNull String codAddress;
	private @NonNull String city;
	private @NonNull String description;
	private @NonNull String adress;
	private @NonNull String state;
	private @NonNull double price;
	private @NonNull Long number;
	private @NonNull Long rooms;
	private @NonNull String block;
	private @NonNull int amount = 0;
	private @NonNull int imageQuantity = 0;
	private @NonNull int virtualImageQuantity = 0;

	public static PropertyDTO createApartment(Apartment apartment) {
		return new ModelMapper().map(apartment, PropertyDTO.class);
	}

	public static PropertyDTO createHouse(House house) {
		return new ModelMapper().map(house, PropertyDTO.class);
	}

	public static PropertyDTO createGround(Ground ground) {
		return new ModelMapper().map(ground, PropertyDTO.class);
	}
}