package com.example.auth.model;

import com.example.auth.dto.UserDTO;
import org.modelmapper.ModelMapper;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Size(min = 2, max = 255, message = "Tamanho mínimo do nome: 2 caracteres")
  @Column(nullable = false)
  private String username;

  @Column(unique = true, nullable = false)
  private String email;

  @Size(min = 8, message = "Tamanho mínimo da senha: 8 caracteres")
  private String password;

  @ElementCollection(fetch = FetchType.EAGER)
  List<Role> roles;
}