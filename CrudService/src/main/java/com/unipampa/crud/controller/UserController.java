package com.unipampa.crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
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
import com.unipampa.crud.service.IPropertyService;
import com.unipampa.crud.service.IUserService;
import com.unipampa.crud.model.Owner;
import com.unipampa.crud.model.Hosting;
import com.unipampa.crud.model.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
@Api(value = "API Crud Users")
public class UserController {

	private IUserService userService;

	private IPropertyService propertyService;

	public UserController(IUserService userService) {
		this.userService = userService;
	}

	@PostMapping("/customer/add")
	@ApiOperation(value = "Adiciona um usuario")
	public ResponseEntity<Void> saveCustomer(@RequestBody @Valid UserDTO userDto) {
		var guest = new User();
		BeanUtils.copyProperties(userDto, guest);
		userService.saveUser(guest);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("/all")
	@ApiOperation(value = "Retorna todos os usuários cadastrados")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.findAllUsers();
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}

	@GetMapping("/find/{email}")
	@ApiOperation(value = "Retorna um usuario pelo email")
	public ResponseEntity<Object> getUserByEmail(@PathVariable("email") String email) {
		Optional<User> user = userService.findUserByEmail(email);
		if (user.isEmpty()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado para esse email!");
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("{id}")
	@ApiOperation(value = "Retorna um usuario pelo id")
	public ResponseEntity<Object> getUserById(@PathVariable("id") Long id) {
		Optional<User> user = userService.findById(id);
		if (user.isEmpty()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}


	@PutMapping("/update/{id}")
	@ApiOperation(value = "Atualiza um usuario pelo id")
	public ResponseEntity<Object> updateUser(@RequestBody @Valid UserDTO userDTO, @PathVariable("id")Long id) {
		Optional<User> user = userService.findById(id);
		if(user.isEmpty()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("usuário não encontrado para esse id, portanto não pode ser atualizado!");
		}
		var userModel = user.get();
		BeanUtils.copyProperties(userDTO, userModel);
		userService.saveUser(userModel);
		return new ResponseEntity<>(userModel, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	@ApiOperation(value = "Remove um usuario pelo seu id")
	public ResponseEntity<Object> deleteUser(@PathVariable("id") Long id) {
		Optional<User> user = userService.findById(id);
		if(user.isEmpty()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado para esse id, portanto não pode ser deletado!");
		}
		userService.deleteUser(id);
		return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado!");
	}

//	@PostMapping("/relation/{id}")
//	public void rentToUser(@PathVariable("id") Long id, Hosting hosting) {
//		Optional<User> user = userService.findById(id);
//		hosting.setUser(user.get());
//		propertyService.saveProperty(hosting);
//	}

}
