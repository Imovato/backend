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
import com.unipampa.crud.interfaces.service.IPropertyService;
import com.unipampa.crud.interfaces.service.IUserService;
import com.unipampa.crud.model.Customer;
import com.unipampa.crud.model.Employee;
import com.unipampa.crud.model.Owner;
import com.unipampa.crud.model.Property;
import com.unipampa.crud.model.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/user")
@Api(value = "API Crud Users")
public class UserController {

	private IUserService userService;

	private IPropertyService propertyService;

	public UserController(IUserService userService) {
		this.userService = userService;
	}

	@GetMapping("/all")
	@ApiOperation(value = "Retorna todos os usuarios cadastrados")
	public ResponseEntity<?> getAllUsers() {
		List<User> users = userService.findAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@PostMapping("/employee/add")
	@ApiOperation(value = "Adiciona um usuario do tipo empregado")
	public ResponseEntity<?> saveEmployee(@RequestBody UserDTO userDto) {
		UserDTO dtoReturn = userService.createEmployee(userDto);
		return new ResponseEntity<>(dtoReturn, HttpStatus.OK);
	}

	@GetMapping("/employee/find/{id}")
	@ApiOperation(value = "Retorna um usuario do tipo empregado pelo id")
	public ResponseEntity<?> getEmployeeById(@PathVariable("id") Long id) {
		Employee employee = userService.findEmployeeById(id);
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}

	@PutMapping("/employee/update")
	@ApiOperation(value = "Atualiza um usuario cadastrado do tipo empregado")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
		Employee updateEmployee = userService.updateEmployee(employee);
		return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
	}

	@PostMapping("/customer/add")
	@ApiOperation(value = "Adiciona um usuario do tipo cliente")
	public ResponseEntity<?> saveCustomer(@RequestBody UserDTO userDto) {
		UserDTO dtoReturn = userService.createCustomer(userDto);
		return new ResponseEntity<>(dtoReturn, HttpStatus.OK);
	}

	@GetMapping("/customer/find/{email}")
	@ApiOperation(value = "Retorna um usuario do tipo cliente pelo email")
	public ResponseEntity<?> getCustomerById(@PathVariable("email") String email) {
		Customer customer = userService.findCustomerByEmail(email);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	@PostMapping("/checkEmail")
	@ApiOperation(value = "Retorna true se o email existe no banco de dados")
	public ResponseEntity<?> getCustomerEmail(@RequestBody UserDTO userDTO) {
		Boolean result = userService.existsByEmail(userDTO.getEmail());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping("/customer/update")
	@ApiOperation(value = "Atualiza um usuario do tipo cliente pelo id")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer) {
		userService.saveUser(customer);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	@PostMapping("/owner/add")
	@ApiOperation(value = "Adiciona um usuario do tipo proprietario")
	public ResponseEntity<?> saveOwner(@RequestBody UserDTO userDto) {
		UserDTO dtoReturn = userService.createOwner(userDto);
		return new ResponseEntity<>(dtoReturn, HttpStatus.OK);
	}

	@GetMapping("/owner/find/{id}")
	@ApiOperation(value = "Retorna um usuario do tipo proprietario pelo id")
	public ResponseEntity<?> getOwnerById(@PathVariable("id") Long id) {
		Owner owner = userService.findOwnerById(id);
		return new ResponseEntity<>(owner, HttpStatus.OK);
	}

	@PutMapping("/owner/update")
	@ApiOperation(value = "Atualiza um usuario do tipo proprietario")
	public ResponseEntity<?> updateOwner(@RequestBody Owner owner) {
		Owner updateOwner = userService.updateOwner(owner);
		return new ResponseEntity<>(updateOwner, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	@ApiOperation(value = "Remove um usuario pelo seu id")
	public void deleteUser(@PathVariable("id") Long id) {
		userService.deleteUser(id);
	}

	@PostMapping("/relation/{id}")
	public void rentToUser(@PathVariable("id") Long id, Property property) {
		User user = userService.findUserById(id);
		property.setUser(user);
		propertyService.saveProperty(property);
	}
}
