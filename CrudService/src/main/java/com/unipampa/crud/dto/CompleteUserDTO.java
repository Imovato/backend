package com.unipampa.crud.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

// import com.unipampa.crud.model.Role;

@Data
public class CompleteUserDTO {

	@ApiModelProperty(position = 0)
	private String username;
	@ApiModelProperty(position = 1)
	private String email;
	@ApiModelProperty(position = 2)
	private String password;
	@ApiModelProperty(position = 3)
	private String cpf;
	@ApiModelProperty(position = 4)
	private String phone;
	@ApiModelProperty(position = 5)
	private String address;

//	@ApiModelProperty(position = 6)
	// List<Role> roles;

	// public List<Role> getRoles() {
	// return roles;
	// }

	// public void setRoles(List<Role> roles) {
	// this.roles = roles;
	// }

}
