package com.unipampa.crud.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<?> getAllUsers () {
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
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
	public ResponseEntity<?> getEmployeeById (@PathVariable("id") Long id) {
        Employee employee = userService.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
	//Put employee
    @PutMapping("/employee/update")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
        Employee updateEmployee = userService.updateEmployee(employee);
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
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
	public ResponseEntity<?> getCustomerById (@PathVariable("id") Long id) {
		Customer customer = userService.findCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
	
	//Put customer
    @PutMapping("/customer/update")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer) {
    	Customer updateCustomer = userService.updateCustomer(customer);
        return new ResponseEntity<>(updateCustomer, HttpStatus.OK);
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
	public ResponseEntity<?> getOwnerById (@PathVariable("id") Long id) {
		Owner owner = userService.findOwnerById(id);
        return new ResponseEntity<>(owner, HttpStatus.OK);
    }
	
	//Put owner
    @PutMapping("/owner/update")
    public ResponseEntity<?> updateOwner(@RequestBody Owner owner) {
    	Owner updateOwner = userService.updateOwner(owner);
        return new ResponseEntity<>(updateOwner, HttpStatus.OK);
    }
	
	//Delete user
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

}
