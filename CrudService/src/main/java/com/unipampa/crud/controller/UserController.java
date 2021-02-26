package com.unipampa.crud.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		employee.setPassword(userDto.getPassword());
		userService.saveUser(employee);
	}
	
	//Get an employee user
	@GetMapping("/employee/find/{id}")
	public Employee getEmployeeById (@PathVariable("id") Long id) {
        Employee employee = userService.findEmployeeById(id);
        return employee;
    }
	//Put employee
    @PutMapping("/employee/update")
    public Employee updateEmployee(@RequestBody Employee employee) {
        Employee updateEmployee = userService.updateEmployee(employee);
        return updateEmployee;
    }
	
	//Add an customer user
	@PostMapping("/customer/add")
	public void saveCustomer(@RequestBody UserDTO userDto) {
		Customer customer = new Customer();
		customer.setEmail(userDto.getEmail());
		customer.setName(userDto.getName());
		customer.setPassword(userDto.getPassword());
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
	
	//Put customer
    @PutMapping("/customer/update")
    public Customer updateCustomer(@RequestBody Customer customer) {
    	Customer updateCustomer = userService.updateCustomer(customer);
        return updateCustomer;
    }
	
	//Add an owner user
	@PostMapping("/owner/add")
	public void saveOwner(@RequestBody UserDTO userDto) {
		Owner owner = new Owner();
		owner.setEmail(userDto.getEmail());
		owner.setName(userDto.getName());
		owner.setPassword(userDto.getPassword());
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
	
	//Put owner
    @PutMapping("/owner/update")
    public Owner updateOwner(@RequestBody Owner owner) {
    	Owner updateOwner = userService.updateOwner(owner);
        return updateOwner;
    }
	
	//Delete user
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

}
