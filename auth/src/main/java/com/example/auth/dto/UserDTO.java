package com.example.auth.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import com.example.auth.model.Role;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Accessors(fluent = true)
@Getter
public class UserDTO {

  @ApiModelProperty(position = 0)
  private final @NonNull String username;
  @ApiModelProperty(position = 1)
  private final @NonNull String email;
  @ApiModelProperty(position = 2)
  private final @NonNull String password;
  @ApiModelProperty(position = 3)
  final @NonNull List<Role> roles;
}