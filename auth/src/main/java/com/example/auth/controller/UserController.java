package com.example.auth.controller;

import javax.servlet.http.HttpServletRequest;

import com.example.auth.dto.CompleteUserDTO;
import com.example.auth.dto.UserDTO;
import com.example.auth.exception.CustomHttpException;
import com.example.auth.model.User;
import com.example.auth.sender.SignupSender;
import com.example.auth.service.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("/users")
@Api(tags = "users")
public class UserController {
  
  @Autowired
  private UserService userService;

  @Autowired
  private SignupSender signupSender;

  @Autowired
  private ModelMapper modelMapper;

  @PostMapping("/signin")
  @ApiOperation(value = "${UserController.signin}")
  @ApiResponses(value = {
    @ApiResponse(code = 400, message = "Algo deu errado"),
    @ApiResponse(code = 422, message = "Nome/senha inválidos")
  })
  public String login(
      @ApiParam("Email") @RequestParam String email,
      @ApiParam("Senha") @RequestParam String password) {
    return userService.signin(email, password);
  }

  @PostMapping("/signup")
  @ApiOperation(value = "${UserController.signup}")
  @ApiResponses(value = {
    @ApiResponse(code = 400, message = "Algo deu errado"),
    @ApiResponse(code = 422, message = "Email em uso"),
  })
  public ResponseEntity<String> signup(@ApiParam("Usuário cadastrando") @RequestBody CompleteUserDTO user) {
    // cria um usuario basico para salvar aqui no microsserviço de autenticação
    // (usuario, email e senha)
    // o usuario real vai para a fila do rabbit para ser consumido pelo CRUD
    User basicUser = new User();
    basicUser.setUsername(user.getUsername());
    basicUser.setEmail(user.getEmail());
    basicUser.setPassword(user.getPassword());
    basicUser.setRoles(user.getRoles());
    try {
      String token = userService.signup(basicUser);
      signupSender.sendMessage(user);
      return new ResponseEntity<>(token, HttpStatus.OK);
    } catch (CustomHttpException e) {
      return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }
  }

  @DeleteMapping(value = "/{email}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "${UserController.delete}", authorizations = {@Authorization(value = "apiKey")})
  @ApiResponses(value = {
    @ApiResponse(code = 400, message = "Algo deu errado"),
    @ApiResponse(code = 403, message = "Acesso negado"),
    @ApiResponse(code = 404, message = "O usuário não existe"),
    @ApiResponse(code = 500, message = "Token JWT expirado ou inválido")
  })
  public String delete(
      @ApiParam("Email") @PathVariable String email) {
    userService.delete(email);
    return email;
  }

  @GetMapping(value = "/{email}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "${UserController.search}", response = UserDTO.class, authorizations = {@Authorization(value = "apiKey")})
  @ApiResponses(value = {
    @ApiResponse(code = 400, message = "Algo deu errado"),
    @ApiResponse(code = 403, message = "Acesso negado"),
    @ApiResponse(code = 404, message = "O usuário não existe"),
    @ApiResponse(code = 500, message = "Token JWT expirado ou inválido")
  })
  public UserDTO search(
      @ApiParam("Email") @PathVariable String email) {
    return modelMapper.map(userService.search(email), UserDTO.class);
  }

  @GetMapping(value = "/me")
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
  @ApiOperation(value = "${UserController.me}", response = UserDTO.class, authorizations = {@Authorization(value = "apiKey")})
  @ApiResponses(value = {
    @ApiResponse(code = 400, message = "Algo deu errado"),
    @ApiResponse(code = 403, message = "Acesso negado"),
    @ApiResponse(code = 500, message = "Token JWT expirado ou inválido")
  })
  public UserDTO whoami(HttpServletRequest req) {
    return modelMapper.map(userService.whoami(req), UserDTO.class);
  }

  @GetMapping("/refresh")
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
  @ApiOperation(value = "${UserController.refresh}", response = UserDTO.class, authorizations = {@Authorization(value = "apiKey")})
  @ApiResponses(value = {
    @ApiResponse(code = 400, message = "Algo deu errado"),
    @ApiResponse(code = 403, message = "Acesso negado"),
  })
  public String refresh(HttpServletRequest req) {
    return userService.refresh(req.getRemoteUser());
  }
}
