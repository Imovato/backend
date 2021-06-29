package com.unipampa.crud.model;

import com.unipampa.crud.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Owner extends User {

	@Column(name = "cpf")
	private String cpf;
	@Column(name = "phone")
	private String phone;
	@Column(name = "address")
	private String address;

	public static Owner createOwner(UserDTO userDTO) {
		return new ModelMapper().map(userDTO, Owner.class);
	}
}