package com.unipampa.crud.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unipampa.crud.dto.UserDTO;
import com.unipampa.crud.entities.Role;
import com.unipampa.crud.entities.User;
import com.unipampa.crud.service.RoleService;
import com.unipampa.crud.service.UserService;
import com.unipampa.crud.validations.ValidationsSignup;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/users")
public class UserResource {

	@Autowired
	private UserService userService;

	@Autowired
	ObjectMapper mapper;

	@Autowired
	List<ValidationsSignup> validations;

	@Autowired
	RoleService roleService;

	@Autowired
	PasswordEncoder passwordEncoder;

	public UserResource(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	@Operation(summary = "Salva um usuario")
	public ResponseEntity<Object> saveUser(@RequestBody UserDTO userDto) {

		this.validations.forEach(e -> e.validate(userDto));

		Role role = roleService.findByName(userDto.type().name()).orElseThrow( () -> new RuntimeException("Role not found"));

		var user = mapper.convertValue(userDto, User.class);
		user.setPassword(passwordEncoder.encode(userDto.password()));
		user.setType(userDto.type());
		user.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
		user.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
		user.getRoles().add(role);
		userService.save(user);
		log.info("User saved successfully username: {}", user.getUserName());
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping
	@Operation(summary = "Retorna todos os usuários cadastrados")
	public ResponseEntity<Page<User>> getAllUsers(
			@PageableDefault(page = 0, size = 3, direction = Sort.Direction.ASC) Pageable pageable) {
		Page<User> users = userService.findAll(pageable);

		return ResponseEntity.status(HttpStatus.OK).body(users);
	}

	@GetMapping("email/{email}")
	@Operation(summary = "Retorna um usuario pelo email")
	public ResponseEntity<Object> getUserByEmail(@PathVariable("email") String email) {
		Optional<User> user = userService.findByEmail(email);
		if (user.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado para esse email!");
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("{id}")
	@Operation(summary = "Retorna um usuario pelo id")
	public ResponseEntity<Object> getUserById(@PathVariable("id") String id) {
		Optional<User> user = userService.findById(id);
		if (user.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}


	@PutMapping("{id}")
	@Operation(summary = "Atualiza um usuario pelo id")
	public ResponseEntity<Object> updateUser(@RequestBody  UserDTO userDTO, @PathVariable("id")String id) {
		Optional<User> user = userService.findById(id);
		if(user.isEmpty()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("usuário não encontrado para esse id, portanto não pode ser atualizado!");
		}
		var userModel = user.get();
		BeanUtils.copyProperties(userDTO, userModel);
		userService.save(userModel);
		return new ResponseEntity<>(userModel, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	@Operation(summary = "Remove um usuario pelo seu id")
	public ResponseEntity<Object> deleteUser(@PathVariable("id") String id) {
		Optional<User> user = userService.findById(id);
		if(user.isEmpty()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado para esse id, portanto não pode ser deletado!");
		}
		userService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado!");
	}
}
