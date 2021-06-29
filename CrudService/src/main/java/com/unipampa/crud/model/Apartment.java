package com.unipampa.crud.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.unipampa.crud.dto.PropertyDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Apartment extends Property {

	@Column(name = "block")
	private String block;

	public static Apartment createApartment(PropertyDTO propertyDTO){
		return new ModelMapper().map(propertyDTO, Apartment.class);
	}
}
