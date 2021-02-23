package com.unipampa.crud.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unipampa.crud.dto.UserDTO;
import com.unipampa.crud.interfaces.service.IUserService;
import com.unipampa.crud.model.Customer;
import com.unipampa.crud.model.Employee;
import com.unipampa.crud.model.Owner;
import com.unipampa.crud.model.User;

@RestController
@RequestMapping("/api/user")
public class UserController {

	private IUserService userService;

	public UserController(IUserService userService) {
		this.userService = userService;
	}

	//Find all users
	@GetMapping("/all")
	public List<User> getAllUsers () {
        List<User> users = userService.findAllUsers();
        return users;
    }
	
	//Add an employee user
	@PostMapping("/employee/add")
	public void saveEmployee(@RequestBody UserDTO userDto) {
		Employee employee = new Employee();
		employee.setEmail(userDto.getEmail());
		employee.setName(userDto.getName());
		employee.setId(userDto.getId());
		userService.saveUser(employee);
	}
	
	//Get an employee user
	@GetMapping("/employee/find/{id}")
	public Employee getEmployeeById (@PathVariable("id") Long id) {
        Employee employee = userService.findEmployeeById(id);
        return employee;
    }
	
	//Add an customer user
	@PostMapping("/customer/add")
	public void saveCustomer(@RequestBody UserDTO userDto) {
		Customer customer = new Customer();
		customer.setEmail(userDto.getEmail());
		customer.setName(userDto.getName());
		customer.setId(userDto.getId());
		customer.setAddress(userDto.getAddress());
		customer.setPhone(userDto.getPhone());
		customer.setCpf(userDto.getCpf());
		userService.saveUser(customer);
	}
	
	//Get an customer user
	@GetMapping("/customer/find/{id}")
	public Customer getCustomerById (@PathVariable("id") Long id) {
		Customer customer = userService.findCustomerById(id);
        return customer;
    }
	
	//Add an owner user
	@PostMapping("/owner/add")
	public void saveOwner(@RequestBody UserDTO userDto) {
		Owner owner = new Owner();
		owner.setEmail(userDto.getEmail());
		owner.setName(userDto.getName());
		owner.setId(userDto.getId());
		owner.setAddress(userDto.getAddress());
		owner.setPhone(userDto.getPhone());
		owner.setCpf(userDto.getCpf());
		userService.saveUser(owner);
	}
	
	//Get an owner user
	@GetMapping("/owner/find/{id}")
	public Owner getOwnerById (@PathVariable("id") Long id) {
		Owner owner = userService.findOwnerById(id);
        return owner;
    }
}
