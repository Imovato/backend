package com.unipampa.crud.dto;

import com.unipampa.crud.model.Customer;
import com.unipampa.crud.model.Employee;
import com.unipampa.crud.model.Owner;
import lombok.*;
import org.modelmapper.ModelMapper;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserDTO {

	private Long id;
	private @NonNull String email;
	private @NonNull String name;
	private @NonNull String password;
	private @NonNull String cpf;
	private @NonNull String phone;
	private @NonNull String address;

	public static UserDTO createEmployee(Employee employee){
		return new ModelMapper().map(employee, UserDTO.class);
	}

	public static UserDTO createCustomer(Customer customer){
		return new ModelMapper().map(customer, UserDTO.class);
	}

	public static UserDTO createOwner(Owner owner){
		return new ModelMapper().map(owner, UserDTO.class);
	}
}
