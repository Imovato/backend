package com.unipampa.crud.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unipampa.crud.dto.UserDTO;
import com.unipampa.crud.interfaces.service.IUserService;
import com.unipampa.crud.model.Customer;
import com.unipampa.crud.model.Employee;
import com.unipampa.crud.model.Owner;

@RestController
@RequestMapping("/api/user")
public class UserController {

	private IUserService userService;

	public UserController(IUserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/employee")
	public void saveEmployee(@RequestBody UserDTO userDto) {
		Employee employee = new Employee();
		employee.setEmail(userDto.getEmail());
		employee.setName(userDto.getName());
		employee.setIdUser(userDto.getIdUser());
		userService.saveUser(employee);
	}
	
	@PostMapping("/customer")
	public void saveCustomer(@RequestBody UserDTO userDto) {
		Customer customer = new Customer();
		customer.setEmail(userDto.getEmail());
		customer.setName(userDto.getName());
		customer.setIdUser(userDto.getIdUser());
		customer.setAddress(userDto.getAddress());
		customer.setPhone(userDto.getPhone());
		customer.setCpf(userDto.getCpf());
		userService.saveUser(customer);
	}
	
	@PostMapping("/owner")
	public void saveOwner(@RequestBody UserDTO userDto) {
		Owner owner = new Owner();
		owner.setEmail(userDto.getEmail());
		owner.setName(userDto.getName());
		owner.setIdUser(userDto.getIdUser());
		owner.setAddress(userDto.getAddress());
		owner.setPhone(userDto.getPhone());
		owner.setCpf(userDto.getCpf());
		userService.saveUser(owner);
	}
}
