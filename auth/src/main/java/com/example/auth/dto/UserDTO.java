package com.example.auth.dto;

import com.example.auth.model.User;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import com.example.auth.model.Role;
import lombok.*;
import org.modelmapper.ModelMapper;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserDTO {

  @ApiModelProperty(position = 0)
  private @NonNull String username;
  @ApiModelProperty(position = 1)
  private @NonNull String email;
  @ApiModelProperty(position = 2)
  private @NonNull String password;
  @ApiModelProperty(position = 3)
  private @NonNull List<Role> roles;

  public static UserDTO createUser(User user) {
    return new ModelMapper().map(user, UserDTO.class);
  }
}